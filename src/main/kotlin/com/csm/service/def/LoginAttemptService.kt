package com.csm.service.def

import org.springframework.stereotype.Service


/*
* Created by I503342 - 08/04/2019
*/
@Service
interface LoginAttemptService {

    fun loginSucceeded(key: String)

    fun loginFailed(key: String)

    fun isBlocked(key: String): Boolean

}