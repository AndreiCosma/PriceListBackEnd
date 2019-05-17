package com.csm.service.def

import com.csm.domain.entity.User
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service


/*
* Created by I503342 - 20/03/2019
*/
@Service
interface JWTUtilService {
    fun getClaims(token: String): Claims
    fun validateToken(token: String): Boolean
    fun generateToken(user: User): String
}