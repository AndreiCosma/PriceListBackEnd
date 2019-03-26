package com.csm.service.impl

import com.csm.domain.dto.UserRegistrationDTO
import com.csm.domain.entity.Authority
import com.csm.domain.entity.Email
import com.csm.domain.entity.Registration
import com.csm.domain.entity.User
import com.csm.domain.repo.EmailRepo
import com.csm.domain.repo.RegistrationRepo
import com.csm.domain.repo.UserRepo
import com.csm.exception.user.UserRegistrationException
import com.csm.service.def.ClientService
import com.csm.service.def.UserRegistrationService
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.transaction.Transactional

@Service
class UserRegistrationServiceImpl(
        val clientService: ClientService,
        val userRepo: UserRepo,
        val registrationRepo: RegistrationRepo,
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val javaMailSender: JavaMailSender,
        val emailRepo: EmailRepo
) : UserRegistrationService {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun registerNewUser(userRegistrationDTO: UserRegistrationDTO) {

        clientService.checkClient(userRegistrationDTO.clientUUID, userRegistrationDTO.clientSecret)
        //Check users registration correctness
        userRegistrationDTO.isUserOk()
        //Check if users exists.
        if (userRepo.findByUsernameU(userRegistrationDTO.userName).isPresent) {
            throw UserRegistrationException("Username already exists.")
        }

        //Check if emailName exists.
        if (emailRepo.findByEmailName(userRegistrationDTO.email).isPresent) {
            throw UserRegistrationException("Email already exists.")
        }

        //Save users
        val user = userRepo.save(userRegistrationDTO.toUser())
        //Add primaryEmail emailName
        user.userEmails.add(Email(baseEntityId = 1L, user = user, emailName = userRegistrationDTO.email, primaryEmail = true))
        //Save changes
        userRepo.save(user)


        val registration = registrationRepo.save(Registration(
                id = UUID.randomUUID().toString(),
                user = user,
                registrationDate = Date(),
                activationDate = Date(),
                active = false
        ))

        //Send confirmation emailName.
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setSubject("Price List account verification")
        simpleMailMessage.setText("Account verification link: http://localhost:8080/register?code=${registration.id}\n " +
                "If you did not registered ignore this emailName. \n Do not reply to this emailName.")
        simpleMailMessage.setTo(userRegistrationDTO.email)
        try {
            javaMailSender.send(simpleMailMessage)
        } catch (e: MailException) {
            //ToDO: Investigate jpa error on delete if email send fails
            //Delete users if emailName fails.
            logger.error("Email error ---> $e")
            userRepo.delete(user)
            registrationRepo.delete(registration)
            throw UserRegistrationException("Failed to send authorization code to emailName: ${userRegistrationDTO.email}.")
        }
    }

    @Transactional
    override fun completeNewUserRegistration(token: String) {
        val registration = registrationRepo.findById(token)

        if (!registration.isPresent) {
            logger.error("No such registration exists $token")
            throw UserRegistrationException("User registration failed.")
        }

        try {
            registrationRepo.save(registration.get().completeRegistration())
            val user = registration.get().user
            user.userAuthorities.add(Authority(users = arrayListOf(user), userAuthority = Authority.ROLE_USER, id = 1L))
            userRepo.save(user.activateUser())
        } catch (e: Exception) {
            //ToDo: Investigate in case of error rollback
            logger.error("User registration exception --> $e")
            throw UserRegistrationException("User registration failed.")
        }
    }

    private fun User.activateUser() = User(
            id = this.id,
            enabled = true,
            usernameU = this.usernameU,
            passwordP = bCryptPasswordEncoder.encode(this.password),
            credentialsNonExpired = true,
            accountNonExpired = true,
            accountNonLocked = true,
            requiresTwoFactor = false,
            userAuthorities = this.authorities,
            userRefreshTokens = this.userRefreshTokens,
            userEmails = this.userEmails,
            lists = this.lists,
            registration = this.registration
    )

    private fun UserRegistrationDTO.toUser() = User(
            id = 1L,
            enabled = false,
            usernameU = this.userName,
            passwordP = bCryptPasswordEncoder.encode(this.password),
            credentialsNonExpired = true,
            accountNonExpired = true,
            accountNonLocked = true,
            requiresTwoFactor = false,
            userAuthorities = arrayListOf(),
            userRefreshTokens = arrayListOf(),
            userEmails = arrayListOf(),
            lists = arrayListOf(),
            registration = null
    )

    private fun Registration.completeRegistration() = Registration(
            id = this.id,
            user = this.user,
            registrationDate = this.registrationDate,
            activationDate = Date(),
            active = true
    )

    private fun UserRegistrationDTO.isUserOk() {
        isValidEmailAddress(this.email)
        isPasswordValid(this.password, this.passwordConfirmation)
        isUsernameValid(this.userName)
    }

    fun isValidEmailAddress(email: String) {
        if (isNullOrEmpty(email)) {
            logger.error("User emailName null or empty")
            throw UserRegistrationException("Email is null or empty.")
        }
        try {
            val emailAddr = InternetAddress(email)
            emailAddr.validate()
        } catch (ex: AddressException) {
            logger.error("User emailName format not valid")
            throw UserRegistrationException("Email format is not valid.")
        }
    }

    fun isPasswordValid(password: String, passwordConfirmation: String) {
        if (isNullOrEmpty(password) || isNullOrEmpty(passwordConfirmation)) {
            logger.error("User password is null or empty")
            throw UserRegistrationException("Password field is empty or null.")
        }
        val pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        if (!password.matches(pattern.toRegex()) && password == passwordConfirmation) {
            logger.error("User password weak or does not match")
            throw UserRegistrationException("Password is too weak or does not match.")
        }

    }

    fun isNullOrEmpty(string: String?): Boolean {
        return string == null || string.isEmpty()
    }

    fun isUsernameValid(username: String) {
        if (isNullOrEmpty(username)) {
            logger.error("User name empty or null")
            throw UserRegistrationException("Username empty or null.")

        }
        if (!username.matches("[A-Za-z0-9_]+".toRegex())) {
            logger.error("User name not valid")
            throw UserRegistrationException("Username not valid.")

        }
    }
}