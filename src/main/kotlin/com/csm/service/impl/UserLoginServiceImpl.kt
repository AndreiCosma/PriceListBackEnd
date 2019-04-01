package com.csm.service.impl

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import com.csm.domain.entity.RefreshToken
import com.csm.domain.repo.ClientRepo
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
@Service
class UserLoginServiceImpl(
        val jwtUtil: JWTUtilService,
        val clientService: ClientService,
        val userRepo: UserRepo,
        val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserLoginService {
    val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO {
        clientService.checkClient(userLoginRequestDTO.clientUUID, userLoginRequestDTO.clientSecret)

        val userOptional = userRepo.findByUsernameU(userLoginRequestDTO.username)

        if (!userOptional.isPresent) {
            logger.error("User ${userLoginRequestDTO.username} does not exist.")
            throw UserLoginException("User not valid.")
        }

        if (!bCryptPasswordEncoder.matches(userLoginRequestDTO.password + userLoginRequestDTO.username, userOptional.get().passwordP)) {
            logger.error("User password: ${userLoginRequestDTO.password} does not match")
            throw UserLoginException("User not valid.")
        }

        val refreshToken = RefreshToken(baseEntityId = 1L,
                user = userOptional.get(),
                refreshToken = UUID.randomUUID().toString(),
                creationDate = Date(),
                deviceUUID = userLoginRequestDTO.deviceUUID)

        userOptional.get().userRefreshTokens.add(refreshToken)

        userRepo.save(userOptional.get())

        return TokenDTO(accessToken = jwtUtil.generateToken(user = userOptional.get()), refreshToken = refreshToken.refreshToken)
    }
}