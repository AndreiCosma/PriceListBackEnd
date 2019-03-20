package com.csm.service.impl

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import com.csm.domain.entity.RefreshToken
import com.csm.domain.repo.ClientRepo
import com.csm.domain.repo.UserRepo
import com.csm.exception.user.UserLoginException
import com.csm.service.def.UserLoginService
import com.csm.util.JWTUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


/*
* Created by I503342 - 19/03/2019
*/
@Service
class UserLoginServiceImpl(
        val jwtUtil: JWTUtil,
        val userRepo: UserRepo,
        val clientRepo: ClientRepo,
        val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserLoginService {
    override fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO {
        val clientOptional = clientRepo.findByClientUUID(userLoginRequestDTO.clientUUID)

        if (!clientOptional.isPresent) {
            throw UserLoginException("Request client not valid.")
        }

        if (clientOptional.get().clientSecret != bCryptPasswordEncoder.encode(userLoginRequestDTO.clientSecret)) {
            throw UserLoginException("Request client not valid.")
        }

        val userOptional = userRepo.findByUsernameU(userLoginRequestDTO.username)

        if (!userOptional.isPresent) {
            throw UserLoginException("User not valid.")
        }

        if (userOptional.get().passwordP != bCryptPasswordEncoder.encode(userLoginRequestDTO.password)) {
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