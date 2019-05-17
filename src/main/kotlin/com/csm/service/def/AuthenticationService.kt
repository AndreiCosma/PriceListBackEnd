package com.csm.service.def

import com.csm.domain.entity.User
import org.springframework.stereotype.Service


/*
* Created by I503342 - 16/04/2019
*/
@Service
interface AuthenticationService {
    fun getAuthenticatedUser(): User
}