package com.csm.service.def

import com.csm.domain.entity.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface UserService {

    fun findById(id: String): Mono<User>

    fun findByUsername(username: String): Mono<User>

}