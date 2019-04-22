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
import com.csm.service.def.AuthorityService
import com.csm.service.def.ClientService
import com.csm.service.def.UserRegistrationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional
import org.springframework.mail.MailException as MailException1

@Service
class UserRegistrationServiceImpl(
        val clientService: ClientService,
        val userRepo: UserRepo,
        val registrationRepo: RegistrationRepo,
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val javaMailSender: JavaMailSender,
        val emailRepo: EmailRepo,
        val authorityService: AuthorityService
) : UserRegistrationService {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${destination.base}")
    lateinit var appBaseDestination: String

    @Transactional
    override fun registerNewUser(userRegistrationDTO: UserRegistrationDTO) {

        clientService.checkClient(userRegistrationDTO.clientName, userRegistrationDTO.clientSecret)
        //Check password
        if (userRegistrationDTO.password != userRegistrationDTO.passwordConfirmation) {
            throw UserRegistrationException("Password does not match.")
        }
        //Check if users exists.
        if (userRepo.findByUsernameU(userRegistrationDTO.userName).isPresent) {
            throw UserRegistrationException("Username already exists.")
        }

        //Check if emailName exists.
        if (emailRepo.findByEmailName(userRegistrationDTO.email).isPresent) {
            throw UserRegistrationException("Email already exists.")
        }

        val savedUser = userRepo.save(userRegistrationDTO.toUser())

        val registration = Registration(
                user = savedUser,
                registrationDate = Date(),
                activationDate = Date(),
                active = false
        )

        val email = Email(
                user = savedUser,
                emailName = userRegistrationDTO.email,
                mainEmail = savedUser
        )
        savedUser.addRegistrationAndEmail(registration, email)

        //Send confirmation emailName.
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setSubject("Price List account verification")
        simpleMailMessage.setText("Account verification link: ${appBaseDestination}register?code=${registration.id}\n " +
                "If you did not registered ignore this email, ignore this. \n Do not reply to this emailName.")
        simpleMailMessage.setTo(userRegistrationDTO.email)
        try {
            javaMailSender.send(simpleMailMessage)
        } catch (e: MailException1) {
            //ToDO: Investigate jpa error on delete if email send fails
            //Delete users if emailName fails.
            logger.error("Email error ---> $e")
            userRepo.delete(savedUser)
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
            registration.get().completeRegistration()
            val user = registration.get().user
            user.userAuthorities.add(authorityService.getAuthority(authority = Authority.ROLE_USER))
            user.activateUser()
        } catch (e: Exception) {
            //ToDo: Investigate in case of error rollback
            logger.error("User registration exception --> $e")
            throw UserRegistrationException("User registration failed.")
        }
    }

    private fun User.addRegistrationAndEmail(registration: Registration, email: Email) =
            this.run {
                mainEmail = email
                this.registration = registration
                this.userEmails = mutableListOf(email)
                this
            }


    private fun User.activateUser() = this.run {
        this.enabled = true
        this
    }

    private fun UserRegistrationDTO.toUser() = User(
            enabled = false,
            usernameU = this.userName.trim(),
            passwordP = bCryptPasswordEncoder.encode(this.password.trim() + this.userName.trim()),
            credentialsNonExpired = true,
            accountNonExpired = true,
            accountNonLocked = true,
            requiresTwoFactor = false,
            userAuthorities = arrayListOf(),
            userRefreshTokens = arrayListOf(),
            userEmails = arrayListOf(),
            ownedLists = arrayListOf(),
            lists = arrayListOf(),
            registration = null,
            mainEmail = null
    )

    private fun Registration.completeRegistration() = this.run {
        this.active = true
        activationDate = Date()
        this
    }
}