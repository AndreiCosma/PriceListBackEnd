package com.csm.domain.dto

data class UserLoginRequestDTO(
        val username: String,
        val password: String,
        val clientUUID: String,
        val clientSecret: String,
        val deviceUUID: String
)