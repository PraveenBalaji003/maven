package org.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private final DataSourceProperties dataSourceProperties;

    public AppConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public JerseyConfig jerseyConfig() {
        return new JerseyConfig();
    }

    static class JerseyConfig extends ResourceConfig {

        public JerseyConfig() {
            this.packages("org.example");
        }
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }
}
