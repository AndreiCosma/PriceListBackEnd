package com.csm.schedule

import com.csm.domain.repo.RefreshTokenRepo
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
@Component
class DeleteOldRefreshTokensTask(
        val refreshTokenRepo: RefreshTokenRepo
) {
    companion object {
        //One hour in millis
        const val HOUR = 60 * 60 * 1000L
        //One day in millis
        const val DAY = 24 * 60 * 60 * 1000L
    }

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Scheduled(fixedRate = DeleteOldNotActiveRegistrationsTask.HOUR)
    fun deleteOldRefreshTokens() {
        try {
            //ToDo: Investigate which is the correct query
            val oldTokens = refreshTokenRepo.findOldTokens()
            refreshTokenRepo.deleteAll(oldTokens)
        } catch (e: Exception) {
            logger.error("Exception in ${this.javaClass.simpleName} --> $e")
        }
    }
}