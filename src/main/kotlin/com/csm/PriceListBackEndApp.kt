package com.csm

import com.csm.service.def.AuthorityService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
class PriceListBackEndApp(
        val authorityService: AuthorityService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        authorityService.init()
    }

}

fun main(args: Array<String>) {
    runApplication<PriceListBackEndApp>(*args)
}
