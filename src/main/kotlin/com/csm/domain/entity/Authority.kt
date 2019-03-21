package com.csm.domain.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Authority(
        @ManyToOne(cascade = [CascadeType.DETACH])
        @JoinColumn(name = "user_id")
        private val user: User,
        private val userAuthority: String,
        id: Long
) : BaseEntity(id), GrantedAuthority {
    companion object {
        const val ROLE_USER = "ROLE_USER"
        const val ROLE_ADMIN = "ROLE_ADMIN"
        const val ROLE_DEVELOPER = "ROLE_DEVELOPER"
    }

    override fun getAuthority() = userAuthority
}