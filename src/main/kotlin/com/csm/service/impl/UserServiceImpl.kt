package com.csm.service.impl

import com.csm.domain.repo.UserRepo
import com.csm.service.def.UserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

class UserServiceImpl(
        private val userRepository: UserRepo,
        private val jdbcScheduler: Scheduler
) : UserService {

    override fun findByUsername(username: String) = Mono.defer {
        Mono.justOrEmpty(userRepository.findByUsernameU(username))
    }.subscribeOn(jdbcScheduler)

    override fun findById(id: String) = Mono.defer {
        Mono.justOrEmpty(userRepository.findById(id))
    }.subscribeOn(jdbcScheduler)

}