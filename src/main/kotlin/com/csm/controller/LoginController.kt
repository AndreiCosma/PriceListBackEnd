package com.csm.controller

import com.csm.domain.dto.AuthRequestDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


/*
* Created by I503342 - 18/03/2019
*/
@RestController
@RequestMapping(name = LoginController.PATH)
@Api(tags = ["Access, Refresh token endpoint used to get tokens."])
class LoginController {
    companion object {
        const val PATH = "/login"
    }

    @PostMapping
    @ApiOperation(value = "Use credentials to get access token and refresh token.")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody authRequestDTO: AuthRequestDTO) {
    }
}