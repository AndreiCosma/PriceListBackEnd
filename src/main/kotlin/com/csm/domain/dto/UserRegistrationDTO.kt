package com.csm.domain.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class UserRegistrationDTO(
        @NotNull
        @NotBlank
        @Email
        val email: String,
        @NotNull
        @NotBlank
        @Pattern(regexp = "[A-Za-z0-9_]+")
        val userName: String,
        @NotNull
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        val password: String,
        @NotNull
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        val passwordConfirmation: String,
        @NotNull
        @NotBlank
        val clientUUID: String,
        @NotNull
        @NotBlank
        val clientSecret: String
)