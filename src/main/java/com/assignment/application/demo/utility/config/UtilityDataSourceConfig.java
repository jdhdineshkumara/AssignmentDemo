package com.assignment.application.demo.utility.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.assignment.application.demo.utility.repository",
        entityManagerFactoryRef = "utilityEntityManagerFactory",
        transactionManagerRef = "utilityTransactionManager"
)
public class UtilityDataSourceConfig {

    @Bean
    @ConfigurationProperties("app.datasource.utility")
    public DataSourceProperties utilityDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource utilityDataSource(
            @Qualifier("utilityDataSourceProperties")
            DataSourceProperties properties) {

        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "utilityEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean utilityEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("utilityDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.assignment.application.demo.utility.domain")
                .persistenceUnit("utility")
                .build();
    }

    @Bean(name = "utilityTransactionManager")
    public JpaTransactionManager utilityTransactionManager(
            @Qualifier("utilityEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
