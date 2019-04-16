package com.csm.service.def

import com.csm.domain.entity.User


/*
* Created by I503342 - 16/04/2019
*/
interface AuthenticationService {
    fun getAuthenticatedUser(): User
}