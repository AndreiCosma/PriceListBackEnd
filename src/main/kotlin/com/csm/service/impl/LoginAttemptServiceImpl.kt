package com.csm.service.impl

import com.csm.service.def.LoginAttemptService
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import java.util.concurrent.ExecutionException


/*
* Created by I503342 - 08/04/2019
*/

@Service
class LoginAttemptServiceImpl : LoginAttemptService {
    companion object {
        const val MAX_ATTEMPT = 10
    }

    private val attemptsCache: LoadingCache<String, Int> = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(object : CacheLoader<String, Int>() {
        override fun load(key: String): Int? {
            return 0
        }
    });

    override fun loginSucceeded(key: String) {
        attemptsCache.invalidate(key)
    }


    override fun loginFailed(key: String) {
        var attempts = 0
        attempts = try {
            attemptsCache.get(key)
        } catch (e: ExecutionException) {
            0
        }

        attempts++
        attemptsCache.put(key, attempts)
    }

    override fun isBlocked(key: String) = try {
        attemptsCache.get(key) >= MAX_ATTEMPT
    } catch (e: ExecutionException) {
        false
    }
}
