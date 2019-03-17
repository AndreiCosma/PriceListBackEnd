package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class User(private val enabled: Boolean,
           private val email: String,
           private val username: String,
           private val password: String,
           private val credentialsNonExpired: Boolean,
           private val accountNonExpired: Boolean,
           private val accountNonLocked: Boolean,
           @OneToMany private val authorities: List<Authority>
) : BaseEntity(), UserDetails {

    override fun getAuthorities() = authorities

    override fun isEnabled() = enabled

    override fun getUsername() = username

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = password

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}