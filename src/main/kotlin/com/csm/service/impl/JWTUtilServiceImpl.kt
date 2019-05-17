package com.csm.service.impl

import com.csm.domain.entity.User
import com.csm.exception.token.TokenNotValidException
import com.csm.service.def.JWTUtilService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Claims
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

class JWTUtilServiceImpl : JWTUtilService {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${spring.jwt.secret}")
    private lateinit var secret: String

    @Value("\${spring.jwt.expiration:3600000}")
    private var expirationTime = 3600000L

    override fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.toByteArray())).parseClaimsJws(token).body
        } catch (e: Exception) {
            logger.error("JWT GET CLAIMS EXCEPTION ---> $e")
            throw TokenNotValidException(e.toString())
        }
    }

    override fun validateToken(token: String) = getExpirationDate(token).after(Date())

    override fun generateToken(user: User): String {

        val createdDate = Date()
        val expirationDate = Date(createdDate.time + expirationTime)

        return Jwts.builder()
                .setClaims(mapOf("userId" to user.id))
                .setSubject(user.username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.toByteArray()))
                .compact()

    }

    fun getExpirationDate(token: String): Date = getClaims(token).expiration

}