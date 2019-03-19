package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "UserEntity")
@Table(name = "USER_TABLE")
class User(
        id: Long,
        val enabled: Boolean,
        val email: String,
        val usernameU: String,
        val passwordP: String,
        val credentialsNonExpired: Boolean,
        val accountNonExpired: Boolean,
        val accountNonLocked: Boolean,
        val refreshToken: String,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER) val userAuthorities: List<Authority>
) : BaseEntity(id), UserDetails {

    override fun getAuthorities() = userAuthorities

    override fun isEnabled() = enabled

    override fun getUsername() = usernameU

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = passwordP

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}