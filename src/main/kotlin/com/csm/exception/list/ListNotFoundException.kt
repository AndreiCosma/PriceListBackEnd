package com.csm.exception.list

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


/*
* Created by I503342 - 17/04/2019
*/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found.")
class ListNotFoundException(
        msg: String
) : RuntimeException(msg)