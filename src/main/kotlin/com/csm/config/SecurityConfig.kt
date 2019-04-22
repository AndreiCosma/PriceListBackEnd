package com.csm.config

import com.csm.config.auth.AuthManager
import com.csm.config.auth.SecurityContextRepository
import com.csm.controller.RegisterController
import com.csm.domain.entity.Authority

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableWebFlux
@Configuration
class SecurityConfig(
        private val authManager: AuthManager,
        private val securityContextRepository: SecurityContextRepository
) {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity) = http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authenticationManager(authManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .pathMatchers("/register").permitAll()
            .pathMatchers("/refresh").permitAll()
            .pathMatchers("/api/v1/odata/v4/NOTES").permitAll()
            .pathMatchers("/api/v1/**").hasAuthority(Authority.ROLE_USER)
            .pathMatchers("/develop/**").hasAuthority(Authority.ROLE_DEVELOPER)
            .pathMatchers("/login", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
            .and().build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}

