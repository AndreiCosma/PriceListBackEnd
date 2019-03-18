package com.csm.config.auth

import com.csm.service.def.UserService
import com.csm.util.JWTUtil
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

@Component
class AuthManager(
        private val jwtUtil: JWTUtil,
        private val userService: UserService
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {

        val authToken = authentication.credentials.toString()
        if (!jwtUtil.validateToken(authToken)) {
            return Mono.empty()
        }

        val id = (jwtUtil.getClaims(authToken)["userId"] as String).toLong()

        return userService.findById(id).map { user ->
            user ?: throw Exception("JWT token valid but user was since deleted from the system.")
            UsernamePasswordAuthenticationToken(user, authToken, user.authorities).also {
                SecurityContextHolder.getContext().authentication = it
            }
        }
    }
}