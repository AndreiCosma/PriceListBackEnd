package com.csm

import com.csm.service.def.AuthorityService
import com.csm.service.def.ClientService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
class PriceListBackEndApp(
        val authorityService: AuthorityService,
        val clientService: ClientService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        authorityService.init()
        if (args.size >= 2) {
            clientService.init(clientName = args[0]!!, clientSecret = args[1]!!)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<PriceListBackEndApp>(*args)
}
