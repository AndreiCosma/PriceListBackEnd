package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "UserEntity")
@Table(name = "USER_TABLE")
class User( val enabled: Boolean,
            val email: String,
            val usernameU: String,
            val passwordP: String,
            val credentialsNonExpired: Boolean,
            val accountNonExpired: Boolean,
            val accountNonLocked: Boolean,
           @OneToMany private val authorities: List<Authority>
) : BaseEntity(), UserDetails {

    override fun getAuthorities() = authorities

    override fun isEnabled() = enabled

    override fun getUsername() = usernameU

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = passwordP

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}