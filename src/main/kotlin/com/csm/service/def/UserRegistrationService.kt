package com.csm.service.def

import com.csm.domain.dto.UserRegistrationDTO
import org.springframework.stereotype.Service

@Service
interface UserRegistrationService {
    fun registerNewUser(userRegistrationDTO: UserRegistrationDTO)
    fun completeNewUserRegistration(token: String)
}