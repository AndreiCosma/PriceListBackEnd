package com.csm.service.def

import com.csm.domain.dto.UserRegistrationDTO

interface UserRegistrationService {
    fun registerNewUser(userRegistrationDTO: UserRegistrationDTO)
    fun completeNewUserRegistration(token: String)
}