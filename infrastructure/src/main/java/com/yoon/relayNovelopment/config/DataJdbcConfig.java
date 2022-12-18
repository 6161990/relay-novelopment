package com.yoon.relayNovelopment.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Primary
@Configuration
public class DataJdbcConfig {

    @Bean
    public DataSource settlementDataSource(@Value("jdbc:postgresql://localhost:9999/relay-novel") String url,
                                           @Value("relay-novel") String username,
                                           @Value("1234") String password) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

}
