package com.csm.service.impl

import com.csm.domain.dto.TokenDTO
import com.csm.domain.entity.RefreshToken
import com.csm.domain.repo.RefreshTokenRepo
import com.csm.exception.token.RefreshTokenNotFoundException
import com.csm.service.def.RefreshTokenService
import com.csm.util.JWTUtil
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
class RefreshTokenServiceImpl(
        val jwtUtil: JWTUtil,
        val refreshTokenRepo: RefreshTokenRepo
) : RefreshTokenService {
    override fun refreshToken(tokenUUID: String): TokenDTO {
        val refreshTokenOptional = refreshTokenRepo.findByRefreshToken(tokenUUID)

        if (!refreshTokenOptional.isPresent) {
            throw RefreshTokenNotFoundException("Refresh token not found.")
        }

        val refreshToken = refreshTokenOptional.get()

        refreshToken.refresh()

        refreshTokenRepo.save(refreshToken)

        return TokenDTO(accessToken = jwtUtil.generateToken(refreshTokenOptional.get().user), refreshToken = refreshToken.refreshToken)
    }

    private fun RefreshToken.refresh() = RefreshToken(
            baseEntityId = this.id,
            user = this.user,
            refreshToken = UUID.randomUUID().toString(),
            deviceUUID = this.deviceUUID,
            creationDate = Date()
    )
}