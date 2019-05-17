package com.csm.service.impl

import com.csm.domain.entity.User
import com.csm.service.def.AuthenticationService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


/*
* Created by I503342 - 16/04/2019
*/

@Service
class AuthenticationServiceImpl : AuthenticationService {
    override fun getAuthenticatedUser(): User = SecurityContextHolder.getContext().authentication.principal as User
}