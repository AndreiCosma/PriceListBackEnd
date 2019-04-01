package com.csm.domain.repo

import com.csm.domain.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
interface RefreshTokenRepo : JpaRepository<RefreshToken, Long> {
    fun findByRefreshToken(refreshToken: String): Optional<RefreshToken>

    @Query("select token from RefreshToken token where date_part('day', age(now(), token.creationDate)) >= 7")
    fun findOldTokens(): MutableList<RefreshToken>
}