package com.csm.domain.dto

data class UserRegistrationDTO( val email: String,
                           val userName: String,
                           val password: String,
                           val passwordConfirmation: String)