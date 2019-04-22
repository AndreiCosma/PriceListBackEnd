package com.csm.domain.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class UserLoginRequestDTO(
        @NotNull
        @NotBlank
        @Pattern(regexp = "[A-Za-z0-9_]+")
        val username: String,
        @NotNull
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        val password: String,
        @NotNull
        @NotBlank
        val clientName: String,
        @NotNull
        @NotBlank
        val clientSecret: String,
        @NotNull
        @NotBlank
        val deviceUUID: String
)