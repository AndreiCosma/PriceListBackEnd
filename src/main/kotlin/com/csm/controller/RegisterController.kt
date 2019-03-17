package com.csm.controller

import com.csm.domain.dto.UserRegistrationDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RegisterController.PATH)
class RegisterController {
    companion object {
        const val PATH = "/register"
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun registerNewUser(@RequestBody userRegistrationDTO: UserRegistrationDTO) {

    }

    fun completeNewUserRegistration() {

    }
}