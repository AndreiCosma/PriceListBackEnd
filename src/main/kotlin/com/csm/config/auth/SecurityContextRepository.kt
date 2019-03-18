package com.csm.config.auth

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken


@Component
class SecurityContextRepository(private val authenticationManager: AuthManager) : ServerSecurityContextRepository {

    override fun save(swe: ServerWebExchange, sc: SecurityContext) : Mono<Void> {
        throw Exception("Not supported yet")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        val request = swe.request
        val authHeader = request.headers.getFirst(HttpHeaders.AUTHORIZATION)

        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val authToken = authHeader.substring(7)
            val auth = UsernamePasswordAuthenticationToken(authToken, authToken)
            authenticationManager.authenticate(auth).map { authentication -> SecurityContextImpl(authentication) }
        } else {
            Mono.empty()
        }
    }
}