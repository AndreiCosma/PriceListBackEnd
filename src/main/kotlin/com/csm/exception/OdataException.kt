package com.csm.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/*
* Created by I503342 - 27/03/2019
*/
@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Bad request.")
class OdataException(reason: String) : RuntimeException(reason)