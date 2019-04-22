package com.csm.domain.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "authority")
class Authority(
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH], mappedBy = "userAuthorities")
        private val users: MutableList<User>,
        @Column(name = "name", length = 128)
        private val userAuthority: String
) : BaseEntity(), GrantedAuthority {
    companion object {
        const val ROLE_USER = "ROLE_USER"
        const val ROLE_ADMIN = "ROLE_ADMIN"
        const val ROLE_DEVELOPER = "ROLE_DEVELOPER"
    }

    override fun getAuthority() = userAuthority
}