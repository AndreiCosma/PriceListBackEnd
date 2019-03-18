package com.csm

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@EnableAutoConfiguration
@SpringBootApplication
@EnableWebFlux
class PriceListBackEndApp

fun main(args: Array<String>) {
    runApplication<PriceListBackEndApp>(*args)
}