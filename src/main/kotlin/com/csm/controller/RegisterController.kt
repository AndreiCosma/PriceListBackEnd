package com.csm.controller

import com.csm.domain.dto.UserRegistrationDTO
import com.csm.service.def.UserRegistrationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RegisterController.PATH)
@Api(tags = ["Access, Refresh token endpoint used to get tokens."])
class RegisterController(
        val userRegistrationService: UserRegistrationService
) {
    companion object {
        const val PATH = "/register"
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register new user. At this point the new user is not active.")
    fun registerNewUser(@RequestBody userRegistrationDTO: UserRegistrationDTO) {
        userRegistrationService.registerNewUser(userRegistrationDTO)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Finish new user registration. After this the requested user will be active.")
    fun completeNewUserRegistration(@RequestParam code: String) {
        userRegistrationService.completeNewUserRegistration(code)
    }
}