package com.csm.controller

import com.csm.domain.dto.ClientDTO
import com.csm.domain.entity.Client
import com.csm.domain.repo.ClientRepo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

/*
* Created by I503342 - 21/03/2019
*/
@RestController
@RequestMapping(DevelopController.PATH)
@Api(tags = ["This controller contains development tools."])
class DevelopController(
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val clientRepo: ClientRepo
) {
    companion object {
        const val PATH = "/develop"
    }

    @GetMapping("/client")
    @ApiOperation(value = "Use this to generate new client credentials for further requests.")
    fun registerNewClient(): ClientDTO {
        val clientSecret = UUID.randomUUID().toString()
        return clientRepo.save(Client(UUID.randomUUID().toString(), clientUUID = UUID.randomUUID().toString(), clientSecret = bCryptPasswordEncoder.encode(clientSecret))).toDTO(notEncodedPassword = clientSecret)

    }

    private fun Client.toDTO(notEncodedPassword: String) = ClientDTO(
            clientName = this.clientUUID,
            clientSecret = notEncodedPassword
    )
}


