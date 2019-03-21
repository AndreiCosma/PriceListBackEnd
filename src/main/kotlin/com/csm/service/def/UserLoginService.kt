package com.csm.service.def

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import javax.transaction.Transactional


/*
* Created by I503342 - 19/03/2019
*/
interface UserLoginService {

    fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO
}