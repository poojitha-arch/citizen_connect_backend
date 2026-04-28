package com.klu.citizen_connect_backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    @Value("${db.url.raw}")
    private String databaseUrl;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driver-class-name:org.postgresql.Driver}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource() {
        String url = databaseUrl;
        String finalUsername = username;
        String finalPassword = password;

        try {
            String cleanUrl = url;
            if (url != null && url.startsWith("jdbc:")) {
                cleanUrl = url.substring(5);
            }

            if (cleanUrl != null && (cleanUrl.startsWith("postgresql://") || cleanUrl.startsWith("postgres://"))) {
                URI uri = new URI(cleanUrl);

                if (uri.getUserInfo() != null) {
                    String[] userInfo = uri.getUserInfo().split(":", 2);
                    finalUsername = userInfo[0];

                    if (userInfo.length > 1) {
                        finalPassword = userInfo[1];
                    }

                    String host = uri.getHost();
                    int port = uri.getPort();
                    String path = uri.getPath();

                    url = "jdbc:postgresql://" + host + (port != -1 ? ":" + port : "") + path;

                    if (uri.getQuery() != null) {
                        url += "?" + uri.getQuery();
                    }

                } else if (!url.startsWith("jdbc:")) {
                    if (url.startsWith("postgresql://")) {
                        url = "jdbc:" + url;
                    } else if (url.startsWith("postgres://")) {
                        url = "jdbc:postgresql://" + url.substring(11);
                    }
                }
            }
        } catch (Exception e) {
            if (url != null && !url.startsWith("jdbc:")) {
                if (url.startsWith("postgresql://")) {
                    url = "jdbc:" + url;
                } else if (url.startsWith("postgres://")) {
                    url = "jdbc:postgresql://" + url.substring(11);
                }
            }
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(finalUsername);
        config.setPassword(finalPassword);
        config.setDriverClassName(driverClassName);

        config.setMaximumPoolSize(3);
        config.setMinimumIdle(1);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setPoolName("CitizenConnectHikariPool");

        return new HikariDataSource(config);
    }
}
