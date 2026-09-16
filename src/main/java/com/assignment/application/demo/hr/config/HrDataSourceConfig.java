package com.assignment.application.demo.hr.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.assignment.application.demo.hr.repository",
        entityManagerFactoryRef = "hrEntityManagerFactory",
        transactionManagerRef = "hrTransactionManager"
)
public class HrDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.hr")
    public DataSourceProperties hrDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource hrDataSource(
            @Qualifier("hrDataSourceProperties")
            DataSourceProperties properties) {

        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "hrEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean hrEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("hrDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.assignment.application.demo.hr.domain")
                .persistenceUnit("hr")
                .build();
    }

    @Bean(name = "hrTransactionManager")
    @Primary
    public JpaTransactionManager hrTransactionManager(
            @Qualifier("hrEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
