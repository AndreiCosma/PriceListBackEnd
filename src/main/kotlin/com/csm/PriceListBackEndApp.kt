package com.csm

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.config.EnableWebFlux


@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
class PriceListBackEndApp

fun main(args: Array<String>) {
    runApplication<PriceListBackEndApp>(*args)
}
