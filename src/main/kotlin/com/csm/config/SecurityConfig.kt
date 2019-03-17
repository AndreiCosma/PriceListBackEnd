package com.csm.config

import com.csm.controller.RegisterController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {
    @Bean
    fun userDetailsService() = MapReactiveUserDetailsService(User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build())


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity) = http.csrf().disable().authorizeExchange().pathMatchers(HttpMethod.POST, RegisterController.PATH).permitAll().and().build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}

