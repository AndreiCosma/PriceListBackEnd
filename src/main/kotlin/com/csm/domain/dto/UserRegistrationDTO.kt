package com.csm.domain.dto

import javax.validation.constraints.NotNull

data class UserRegistrationDTO(
        @NotNull
        val email: String,
        @NotNull
        val userName: String,
        @NotNull
        val password: String,
        @NotNull
        val passwordConfirmation: String,
        @NotNull
        val clientUUID: String,
        @NotNull
        val clientSecret: String
)