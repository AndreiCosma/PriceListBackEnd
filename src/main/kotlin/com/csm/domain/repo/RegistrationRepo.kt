package com.csm.domain.repo

import com.csm.domain.entity.Registration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*
import java.util.stream.Stream

/*
* Created by I503342 - 19/03/2019
*/
interface RegistrationRepo : JpaRepository<Registration, String> {

    @Modifying
    @Query("DELETE FROM Registration r where r.active = false and r.registrationDate < :timestamp")
    fun deleteExpiredActivation(@Param("timestamp") timestamp: Date): Stream<Registration>

}