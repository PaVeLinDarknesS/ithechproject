package com.itech.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


public class HibernateConfig {


    @Bean("dataSource")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        String databaseName = "jdbc:h2:mem:library;DB_CLOSE_DELAY=-1";
        return builder
                .setType(EmbeddedDatabaseType.H2)
                //.setName("helpDesk")
                .setScriptEncoding("UTF-8")
                //.continueOnError(true)
                //.ignoreFailedDrops(true)
                .build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.itech.library.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        return properties;
    }
}
