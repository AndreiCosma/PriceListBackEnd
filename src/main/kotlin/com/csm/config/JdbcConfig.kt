package com.csm.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors

@Configuration
class JdbcConfig {

    @Value("\${spring.datasource.maximum-pool-size:200}")
    var connectionPoolSize: Int = 200

    @Bean
    fun jdbcScheduler() = Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize))

    @Bean
    fun transactionTemplate(transactionManager: PlatformTransactionManager) = TransactionTemplate(transactionManager)

}