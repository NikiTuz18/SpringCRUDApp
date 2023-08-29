package ru.nikituz.simplespringcrudapp.configurers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.nikituz.simplespringcrudapp.configurers.properties.DataSourceProperties;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.nikituz.simplespringcrudapp")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    private final DataSourceProperties dataSourceProperties;

    @Autowired
    public ApplicationConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(this.dataSource());
        factory.setPackagesToScan("ru.nikituz.simplespringcrudapp");
        factory.setHibernateProperties(this.hibernateProperties());
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.dataSourceProperties.getDriverClassName());
        dataSource.setUrl(this.dataSourceProperties.getUrl());
        dataSource.setUsername(this.dataSourceProperties.getUsername());
        dataSource.setPassword(this.dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("show_sql", "true"); // Нужно на этапе разработки и тестирования, потом убрать
        return properties;
    }
}
