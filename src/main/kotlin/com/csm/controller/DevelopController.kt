package com.csm.controller

import com.csm.domain.dto.ClientDTO
import com.csm.domain.entity.Client
import com.csm.domain.repo.ClientRepo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.*

/*
* Created by I503342 - 21/03/2019
*/
@RestController
@RequestMapping(path = [DevelopController.PATH])
@Api(tags = ["This controller contains development tools."])
//@Profile(value = ["local"])
class DevelopController(
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val clientRepo: ClientRepo
) {
    companion object {
        const val PATH = "/develop"
    }

    @GetMapping(path = ["/client"])
    @ApiOperation(value = "Use this to generate new client credentials for further requests.")
    fun registerNewClient(): Mono<ClientDTO> {
        val clientSecret = UUID.randomUUID().toString()
        return clientRepo.save(Client(clientUUID = UUID.randomUUID().toString(), clientSecret = bCryptPasswordEncoder.encode(clientSecret))).toDTO(notEncodedPassword = clientSecret).toMono()

    }

    private fun Client.toDTO(notEncodedPassword: String) = ClientDTO(
            clientName = this.clientUUID,
            clientSecret = notEncodedPassword
    )
}


