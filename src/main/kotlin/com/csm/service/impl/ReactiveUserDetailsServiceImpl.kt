package com.csm.service.impl

import com.csm.domain.repo.UserRepo
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import reactor.core.publisher.Mono

class ReactiveUserDetailsServiceImpl(private val userRepo: UserRepo) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> {
        if (!userRepo.findByUsername(username).isPresent) {
            throw UsernameNotFoundException("User $username not found.")
        }
        return Mono.just(userRepo.findByUsername(username).get())
    }
}