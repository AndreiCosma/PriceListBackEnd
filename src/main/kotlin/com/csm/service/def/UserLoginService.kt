package com.csm.service.def

import com.csm.domain.dto.TokenDTO
import com.csm.domain.dto.UserLoginRequestDTO
import org.springframework.stereotype.Service
import javax.transaction.Transactional


/*
* Created by I503342 - 19/03/2019
*/
@Service
interface UserLoginService {

    fun loginUser(userLoginRequestDTO: UserLoginRequestDTO): TokenDTO
}