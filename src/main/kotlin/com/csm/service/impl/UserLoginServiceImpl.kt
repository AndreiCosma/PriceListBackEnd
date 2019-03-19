package com.csm.service.impl

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import com.csm.service.def.UserLoginService
import com.csm.service.def.UserService
import org.springframework.stereotype.Service


/*
* Created by I503342 - 19/03/2019
*/
@Service
class UserLoginServiceImpl(val userService: UserService) : UserLoginService {
    override fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}