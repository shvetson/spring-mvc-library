package ru.shvets.springmvcapp4.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.01.2023 23:37
 */

@Configuration
@PropertySource("classpath:application.yml")
class SpringConfig(
    private val environment: Environment
) {

    @Bean
    fun datasource(): DataSource {
        val dataSource: DriverManagerDataSource = DriverManagerDataSource()
        environment.getProperty("spring.datasource.driver-class-name")?.let { dataSource.setDriverClassName(it) }
        dataSource.url=environment.getProperty("url")
        dataSource.username=environment.getProperty("spring.datasource.username")
        dataSource.password=environment.getProperty("spring.datasource.password")
        return dataSource
    }

    @Bean
    fun jdbcTemplate(): JdbcTemplate{
        return JdbcTemplate(datasource())
    }
}