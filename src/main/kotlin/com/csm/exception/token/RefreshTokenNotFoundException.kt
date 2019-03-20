package com.csm.exception.token

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/*
* Created by I503342 - 20/03/2019
*/
@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Refresh token not valid.")
class RefreshTokenNotFoundException(reason: String) : RuntimeException(reason)