package com.csm.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurationSupport
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@Configuration
@EnableSwagger2WebFlux
class SwaggerConfig : WebFluxConfigurationSupport() {

    @Bean
    fun api() = Docket(DocumentationType.SWAGGER_2)
        .produces(setOf("application/json"))
        .consumes(setOf("application/json"))
        .securityContexts(listOf(
            SecurityContext.builder()
                    .securityReferences(listOf(
                        SecurityReference(
                            "JWT",
                            arrayOf(AuthorizationScope("global", "accessEverything"))
                        )
                    ))
                    .forPaths(PathSelectors.any())
                    .build()
        ))
        .securitySchemes(listOf(
            ApiKey("JWT", "Authorization", "header")
        ))
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()

    @Bean
    fun jackson2JsonEncoder(mapper: ObjectMapper) = Jackson2JsonEncoder(mapper)

    @Bean
    fun jackson2JsonDecoder(mapper: ObjectMapper) = Jackson2JsonDecoder(mapper)

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/")
    }

}