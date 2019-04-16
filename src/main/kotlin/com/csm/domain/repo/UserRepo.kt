package com.csm.domain.repo

import com.csm.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


interface UserRepo : JpaRepository<User, String> {
    fun findByUsernameU(usernameU: String): Optional<User>
}