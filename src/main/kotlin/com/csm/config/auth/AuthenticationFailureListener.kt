package com.csm.config.auth

import com.csm.service.def.LoginAttemptService
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


/*
* Created by I503342 - 08/04/2019
*/
@Component
class AuthenticationFailureListener : ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private val loginAttemptService: LoginAttemptService? = null

    override fun onApplicationEvent(e: AuthenticationFailureBadCredentialsEvent) {
        val auth = e.authentication.details as WebAuthenticationDetails

        loginAttemptService!!.loginFailed(auth.remoteAddress)
    }
}