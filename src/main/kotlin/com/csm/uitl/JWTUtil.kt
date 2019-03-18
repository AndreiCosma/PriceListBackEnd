package com.csm.uitl

import com.csm.domain.entity.User
import org.springframework.stereotype.Component
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Claims
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTUtil {

    companion object {
        const val SERIAL_VERSION_UID = 1L
    }

    @Value("\${spring.jwt.secret}")
    private lateinit var secret: String

    @Value("\${spring.jwt.expiration:28800}")
    private var expirationTime: Long = 28800

    fun getClaims(token: String): Claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.toByteArray())).parseClaimsJws(token).body

    fun getExpirationDate(token: String): Date = getClaims(token).expiration

    fun validateToken(token: String) = getExpirationDate(token).after(Date())

    fun generateToken(user: User): String {

        val createdDate = Date()
        val expirationDate = Date(createdDate.time + expirationTime * 1000)

        return Jwts.builder()
                .setClaims(mapOf("userId" to "${user.id}"))
                .setSubject(user.username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.toByteArray()))
                .compact()

    }
}