package com.ekino.r2dbc.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.ekino.r2dbc.repository")
public class ReactiveDbConfig extends AbstractR2dbcConfiguration {

    @Bean
    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        var config = PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .database("test")
                .username("foo")
                .password("foo")
                .build();
        return new PostgresqlConnectionFactory(config);
    }
}
