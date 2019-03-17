package com.csm.domain.repo

import com.csm.domain.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepo : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}