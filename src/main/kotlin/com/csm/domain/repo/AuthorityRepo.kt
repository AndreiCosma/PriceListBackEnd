package com.csm.domain.repo

import com.csm.domain.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface AuthorityRepo : JpaRepository<Authority, String> {
    fun findByUserAuthority(userAuthority: String): Optional<Authority>
}