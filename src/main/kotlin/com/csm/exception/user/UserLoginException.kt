package com.csm.exception.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/*
* Created by I503342 - 20/03/2019
*/
@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "User login bad request.")
class UserLoginException(reason: String) : RuntimeException(reason)