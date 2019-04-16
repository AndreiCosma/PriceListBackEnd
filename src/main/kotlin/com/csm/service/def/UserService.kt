package com.csm.service.def

import com.csm.domain.entity.User
import reactor.core.publisher.Mono

interface UserService {

    fun findById(id: String): Mono<User>

    fun findByUsername(username: String): Mono<User>

}