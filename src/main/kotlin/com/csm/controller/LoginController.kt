package com.csm.controller

import com.csm.domain.dto.UserLoginRequestDTO
import com.csm.service.def.UserLoginService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


/*
* Created by I503342 - 18/03/2019
*/
@RestController
@RequestMapping(name = LoginController.PATH)
@Api(tags = ["Access, Refresh token endpoint used to get tokens."])
class LoginController(val userLoginService: UserLoginService) {
    companion object {
        const val PATH = "/login"
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Use credentials to get access token and refresh token.")
    fun login(@RequestBody userLoginRequestDTO: UserLoginRequestDTO) = Mono.just(userLoginService.loginUser(userLoginRequestDTO))
}