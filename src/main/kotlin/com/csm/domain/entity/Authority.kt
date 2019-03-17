package com.csm.domain.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity

@Entity
class Authority(private val authority: String) : BaseEntity(), GrantedAuthority {
    companion object {
        const val ROLE_USER = "ROLE_USER"
        const val ROLE_ADMIN = "ROLE_ADMIN"
        const val ROLE_DEVELOPER = "ROLE_DEVELOPER"
    }

    override fun getAuthority() = authority
}