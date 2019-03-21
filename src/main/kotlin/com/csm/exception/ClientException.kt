package com.csm.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/*
* Created by I503342 - 21/03/2019
*/
@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Client credentials not valid.")
class ClientException(msg: String) : RuntimeException(msg)