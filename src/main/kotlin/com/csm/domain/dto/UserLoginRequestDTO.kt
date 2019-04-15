package com.csm.domain.dto

import javax.validation.constraints.NotNull

data class UserLoginRequestDTO(
        @NotNull
        val username: String,
        @NotNull
        val password: String,
        @NotNull
        val clientUUID: String,
        @NotNull
        val clientSecret: String,
        @NotNull
        val deviceUUID: String
)