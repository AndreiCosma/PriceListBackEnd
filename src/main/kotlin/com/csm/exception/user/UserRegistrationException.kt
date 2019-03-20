package com.csm.exception.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Bad Request.")
class UserRegistrationException(msg: String) : RuntimeException(msg)