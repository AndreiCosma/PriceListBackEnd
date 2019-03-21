package com.csm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.config.EnableWebFlux


@EnableWebFlux
@EnableScheduling
@SpringBootApplication
class PriceListBackEndApp

fun main(args: Array<String>) {
    runApplication<PriceListBackEndApp>(*args)
}
