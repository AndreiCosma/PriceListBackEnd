package com.csm.domain.dto

import com.csm.domain.entity.Authority
import com.csm.domain.entity.User

class UserRegistrationDTO(private val email: String,
                          private val userName: String,
                          private val password: String,
                          private val passwordConfirmation: String) {
    fun toUser() = User(
            enabled = false,
            email = this.email,
            username = this.userName,
            password = this.password,
            credentialsNonExpired = true,
            accountNonExpired = true,
            accountNonLocked = false,
            authorities = listOf(Authority(authority = Authority.ROLE_USER))
    )
}