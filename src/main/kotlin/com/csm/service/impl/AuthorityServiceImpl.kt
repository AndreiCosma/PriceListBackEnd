package com.csm.service.impl

import com.csm.domain.entity.Authority
import com.csm.domain.repo.AuthorityRepo
import com.csm.exception.AuthorityNotFoundException
import com.csm.service.def.AuthorityService
import org.springframework.stereotype.Service
import java.util.*


/*
* Created by I503342 - 17/04/2019
*/
@Service
class AuthorityServiceImpl(
        val authorityRepo: AuthorityRepo
) : AuthorityService {
    override fun init() {
        if (!authorityRepo.findByUserAuthority(Authority.ROLE_USER).isPresent) {
            authorityRepo.save(Authority(users = mutableListOf(), userAuthority = Authority.ROLE_USER))
        }
        if (!authorityRepo.findByUserAuthority(Authority.ROLE_ADMIN).isPresent) {
            authorityRepo.save(Authority(users = mutableListOf(), userAuthority = Authority.ROLE_ADMIN))
        }
        if (!authorityRepo.findByUserAuthority(Authority.ROLE_DEVELOPER).isPresent) {
            authorityRepo.save(Authority(users = mutableListOf(), userAuthority = Authority.ROLE_DEVELOPER))
        }
    }

    override fun getAuthority(authority: String) = authorityRepo.findByUserAuthority(authority).get()
}