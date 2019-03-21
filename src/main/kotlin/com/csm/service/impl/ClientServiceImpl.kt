package com.csm.service.impl

import com.csm.domain.repo.ClientRepo
import com.csm.exception.ClientException
import com.csm.service.def.ClientService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


/*
* Created by I503342 - 21/03/2019
*/

@Service
class ClientServiceImpl(
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val clientRepo: ClientRepo
) : ClientService {

    val logger = LoggerFactory.getLogger(this.javaClass)

    override fun checkClient(clientName: String, clientSecret: String) {
        val clientOptional = clientRepo.findByClientUUID(clientName)

        if (!clientOptional.isPresent) {
            logger.error("Client not found.")
            throw ClientException("Request client not valid.")
        }

        if (!bCryptPasswordEncoder.matches(clientSecret, clientOptional.get().clientSecret)) {
            logger.error("Client password not matching.")
            throw ClientException("Request client not valid.")
        }
    }
}