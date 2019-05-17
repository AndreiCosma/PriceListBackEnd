package com.csm.service.def

import com.csm.domain.entity.Authority
import org.springframework.stereotype.Service


/*
* Created by I503342 - 17/04/2019
*/
@Service

interface AuthorityService {
    fun init()
    fun getAuthority(authority: String): Authority
}