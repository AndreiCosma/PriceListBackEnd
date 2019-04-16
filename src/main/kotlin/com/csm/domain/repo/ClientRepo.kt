package com.csm.domain.repo

import com.csm.domain.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/*
* Created by I503342 - 20/03/2019
*/
interface ClientRepo : JpaRepository<Client, String> {
    fun findByClientUUID(clientUUID: String): Optional<Client>
}