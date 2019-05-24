package com.csm.schedule

import com.csm.domain.repo.RegistrationRepo
import com.csm.domain.repo.UserRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.scheduling.annotation.Scheduled
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*


/*
* Created by I503342 - 19/03/2019
*/

@Component
class DeleteOldNotActiveRegistrationsTask(
        val registrationRepo: RegistrationRepo,
        val userRepo: UserRepo
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Scheduled(fixedRate = HOUR)
    fun delteInactiveRegistration() {
        registrationRepo.deleteExpiredActivation(Date.from(
                Instant.now().minus(1L, ChronoUnit.HOURS)
        ))
    }

    companion object {
        //One hour in millis
        const val HOUR = 60 * 60 * 1000L
        //One day in millis
        const val DAY = 24 * 60 * 60 * 1000L
    }
}