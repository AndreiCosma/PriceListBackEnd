package com.csm.service.def

import com.csm.domain.dto.TokenDTO
import org.springframework.stereotype.Service


/*
* Created by I503342 - 20/03/2019
*/

interface RefreshTokenService {
    fun refreshToken(tokenUUID: String): TokenDTO
}