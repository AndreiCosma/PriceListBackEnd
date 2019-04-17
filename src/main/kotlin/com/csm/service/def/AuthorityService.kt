package com.csm.service.def

import com.csm.domain.entity.Authority


/*
* Created by I503342 - 17/04/2019
*/
interface AuthorityService {
    fun init()
    fun getAuthority(authority: String): Authority
}