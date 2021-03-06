package com.csm.controller

import com.csm.domain.dto.UserRegistrationDTO
import com.csm.service.def.UserRegistrationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = [RegisterController.PATH])
@Api(tags = ["Access, Refresh token endpoint used to get tokens."])
class RegisterController(
        val userRegistrationService: UserRegistrationService
) {

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Register new users. At this point the new users is not active.")
    fun registerNewUser(@RequestBody userRegistrationDTO: UserRegistrationDTO) =
            userRegistrationService.registerNewUser(userRegistrationDTO)


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Finish new users registration. After this the requested users will be active.")
    fun completeNewUserRegistration(@RequestParam code: String) =
            userRegistrationService.completeNewUserRegistration(code)

    companion object {
        const val PATH = "/register"
    }
}