package com.csm.schedule

import com.csm.domain.repo.RegistrationRepo
import com.csm.domain.repo.UserRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.scheduling.annotation.Scheduled


/*
* Created by I503342 - 19/03/2019
*/

@Component
class DeleteOldNotActiveRegistrationsTask(
        val registrationRepo: RegistrationRepo,
        val userRepo: UserRepo
) {
    companion object {
        //One hour in millis
        const val HOUR = 60 * 60 * 1000L
        //One day in millis
        const val DAY = 24 * 60 * 60 * 1000L
    }

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Scheduled(fixedRate = HOUR)
    fun delteInactiveRegistration() {
        try {
            registrationRepo.findByActiveFalse().forEach {
                if (it.registrationDate.time > System.currentTimeMillis() - HOUR) {
                    userRepo.deleteById(it.userId)
                    registrationRepo.delete(it)
                }
            }
        } catch (e: Exception) {
            logger.error("Exception in ${this.javaClass.simpleName} --> $e")
        }
    }
}