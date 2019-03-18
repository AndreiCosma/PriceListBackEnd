package com.csm.service.impl

import com.csm.domain.dto.UserRegistrationDTO
import com.csm.domain.entity.Authority
import com.csm.domain.entity.User
import com.csm.domain.repo.UserRepo
import com.csm.exception.UserRegistrationException
import com.csm.service.def.UserRegistrationService
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

@Service
class UserRegistrationServiceImpl(val userRepo: UserRepo,
                                  val bCryptPasswordEncoder: BCryptPasswordEncoder,
                                  val javaMailSender: JavaMailSender
) : UserRegistrationService {
    private val logger = LoggerFactory.getLogger(this.javaClass)


    override fun registerNewUser(userRegistrationDTO: UserRegistrationDTO) {
        //Check user registration correctness
        userRegistrationDTO.isUserOk()
        //ToDO: Check if user exists.

        //Save user
        val user = userRepo.save(userRegistrationDTO.toUser())

        //Send confirmation email.
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setSubject("Price List account verification")
        simpleMailMessage.setText("Account verification link http://localhost:8080/register/${user.id}")
        simpleMailMessage.setTo(userRegistrationDTO.email)
        try {
            javaMailSender.send(simpleMailMessage)
        } catch (e: MailException) {
            //Delete user if email fails.
            //ToDO: Investigate delete conflict if mail fails
            logger.error("Email error ---> ${e.toString()}")
            userRepo.delete(user)
            throw UserRegistrationException("Failed to send authorization code to email: ${userRegistrationDTO.email}.")
        }
    }

    override fun completeNewUserRegistration(token: String) {
        try {
            userRepo.save(userRepo.getOne(token.toLong()).activateUser())
        } catch (e: Exception) {
            throw UserRegistrationException("User registration failed.")
        }
    }

    fun User.activateUser() = User(
            enabled = true,
            email = this.email,
            usernameU = this.usernameU,
            passwordP = this.passwordP,
            credentialsNonExpired = this.credentialsNonExpired,
            accountNonExpired = this.accountNonExpired,
            accountNonLocked = this.accountNonLocked,
            authorities = this.authorities
    )

    fun UserRegistrationDTO.toUser() = User(
            enabled = false,
            email = this.email,
            usernameU = this.userName,
            passwordP = bCryptPasswordEncoder.encode(this.password),
            credentialsNonExpired = true,
            accountNonExpired = true,
            accountNonLocked = true,
            authorities = listOf(Authority(authority = Authority.ROLE_USER))
    )

    fun UserRegistrationDTO.isUserOk() {
        isValidEmailAddress(this.email)
        isPasswordValid(this.password, this.passwordConfirmation)
        isUsernameValid(this.userName)
    }

    fun isValidEmailAddress(email: String) {
        if (isNullOrEmpty(email)) {
            throw UserRegistrationException("Email is null or empty.")
        }
        try {
            val emailAddr = InternetAddress(email)
            emailAddr.validate()
        } catch (ex: AddressException) {
            throw UserRegistrationException("Email format is not valid.")
        }
    }

    fun isPasswordValid(password: String, passwordConfirmation: String) {
        if (isNullOrEmpty(password) || isNullOrEmpty(passwordConfirmation)) {
            throw UserRegistrationException("Password field is empty or null.")
        }
        val pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        if (!password.matches(pattern.toRegex()) && password == passwordConfirmation) {
            throw UserRegistrationException("Password is too weak or does not match.")
        }

    }

    fun isNullOrEmpty(string: String?): Boolean {
        return string == null || string.isEmpty()
    }

    fun isUsernameValid(username: String) {
        if (isNullOrEmpty(username)) {
            throw UserRegistrationException("Username empty or null.")

        }
        if (!username.matches("[A-Za-z0-9_]+".toRegex())) {
            throw UserRegistrationException("Username not valid.")

        }
    }
}