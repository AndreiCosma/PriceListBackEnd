package com.csm.domain.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "UserEntity")
@Table(name = "USER_TABLE")
class User(
        id: Long,
        val enabled: Boolean,
        val usernameU: String,
        val passwordP: String,
        val credentialsNonExpired: Boolean,
        val accountNonExpired: Boolean,
        val accountNonLocked: Boolean,
        val requiresTwoFactor: Boolean,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER) val userAuthorities: MutableList<Authority>,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY) val userRefreshTokens: MutableList<RefreshToken>,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY) val userEmails: MutableList<Email>
) : BaseEntity(id), UserDetails {

    override fun getAuthorities() = userAuthorities

    override fun isEnabled() = enabled

    override fun getUsername() = usernameU

    override fun isCredentialsNonExpired() = credentialsNonExpired

    override fun getPassword() = passwordP

    override fun isAccountNonExpired() = accountNonExpired

    override fun isAccountNonLocked() = accountNonLocked
}