package com.csm.listener

import com.csm.service.def.LoginAttemptService
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component


/*
* Created by I503342 - 08/04/2019
*/
//Todo: finish DDOS attack safety https://www.baeldung.com/spring-security-block-brute-force-authentication-attempts
@Component
class AuthenticationSuccessEventListener(
        val loginAttemptService: LoginAttemptService
) : ApplicationListener<AuthenticationSuccessEvent> {
    override fun onApplicationEvent(event: AuthenticationSuccessEvent) {
        val auth = event.authentication.details as WebAuthenticationDetails
        loginAttemptService.loginSucceeded(auth.remoteAddress)
    }
}