package com.csm.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Bad Request.")
class UserRegistrationException(msg: String) : Exception(msg)