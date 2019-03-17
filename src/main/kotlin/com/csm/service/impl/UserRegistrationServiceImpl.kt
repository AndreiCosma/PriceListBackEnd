package com.csm.service.impl

import com.csm.domain.dto.UserRegistrationDTO
import com.csm.domain.entity.Authority
import com.csm.domain.entity.User
import com.csm.domain.repo.UserRepo
import com.csm.service.def.UserRegistrationService

class UserRegistrationServiceImpl(val userRepo: UserRepo) : UserRegistrationService {


    override fun registerNewUser(userRegistrationDTO: UserRegistrationDTO) {
        userRepo.save(userRegistrationDTO.toUser())
    }

    override fun completeNewUserRegistration(token: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}