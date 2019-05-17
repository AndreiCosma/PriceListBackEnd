package com.csm.service.impl

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import com.csm.domain.entity.RefreshToken
import com.csm.domain.repo.UserRepo
import com.csm.exception.user.UserLoginException
import com.csm.service.def.ClientService
import com.csm.service.def.JWTUtilService
import com.csm.service.def.UserLoginService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


/*
* Created by I503342 - 19/03/2019
*/

class UserLoginServiceImpl(
        val jwtUtil: JWTUtilService,
        val clientService: ClientService,
        val userRepo: UserRepo,
        val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserLoginService {
    val logger = LoggerFactory.getLogger(this.javaClass)!!

    @Transactional
    override fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO {
        clientService.checkClient(userLoginRequestDTO.clientName.trim(), userLoginRequestDTO.clientSecret.trim())

        val userOptional = userRepo.findByUsernameU(userLoginRequestDTO.username.trim())

        if (!userOptional.isPresent) {
            logger.error("User ${userLoginRequestDTO.username.trim()} does not exist.")
            throw UserLoginException("User not valid.")
        }

        if (!bCryptPasswordEncoder.matches(userLoginRequestDTO.password.trim() + userLoginRequestDTO.username.trim(), userOptional.get().passwordP.trim())) {
            logger.error("User password: ${userLoginRequestDTO.password.trim()} does not match")
            throw UserLoginException("User not valid.")
        }

        val refreshToken = RefreshToken(
                user = userOptional.get(),
                refreshToken = UUID.randomUUID().toString(),
                creationDate = Date(),
                deviceUUID = userLoginRequestDTO.deviceUUID.trim())

        userOptional.get().userRefreshTokens.add(refreshToken)

        return TokenDTO(accessToken = jwtUtil.generateToken(user = userOptional.get()), refreshToken = refreshToken.refreshToken)
    }
}