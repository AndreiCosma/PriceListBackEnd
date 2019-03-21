package com.csm.domain.repo

import com.csm.domain.entity.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
interface RefreshTokenRepo : JpaRepository<RefreshToken, Long> {
    fun findByRefreshToken(refreshToken: String): Optional<RefreshToken>

    @Query("select token from RefreshToken token where datediff(currentDate,token.creationDate)>=7")
    fun findOldTokens(@Param("currentDate") currentDate: Date): MutableList<RefreshToken>
}