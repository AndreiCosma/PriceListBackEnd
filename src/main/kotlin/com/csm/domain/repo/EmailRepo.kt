package com.csm.domain.repo

import com.csm.domain.entity.Email
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
interface EmailRepo : JpaRepository<Email, Long> {
    fun findByEmail(email: String): Optional<Email>
}