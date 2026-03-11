package com.techouts.ecommerce.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {

    private Environment env;

    @Autowired
    HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig ();
        hikariConfig.setDriverClassName (env.getProperty ("database.driver"));
        hikariConfig.setJdbcUrl (env.getProperty ("database.jdbc_url"));
        hikariConfig.setUsername (env.getProperty ("database.username"));
        hikariConfig.setPassword (env.getProperty ("database.password"));
        hikariConfig.setMaximumPoolSize (Integer.parseInt (env.getProperty ("database.max_pool_size")));
        return new HikariDataSource (hikariConfig);

    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean ();
        sessionFactoryBean.setDataSource (dataSource); // this will work since spring generate the dataSource bean and injects it automatically
        sessionFactoryBean.setPackagesToScan ("com.techouts.ecommerce.model");

        Properties props = new Properties();
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        props.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        props.put("hibernate.cache.use_second_level_cache", env.getProperty ("hibernate.cache.use_second_level_cache"));
        props.put("hibernate.cache.use_query_cache", env.getProperty ("hibernate.cache.use_query_cache"));
        props.put("hibernate.cache.region.factory_class", env.getProperty ("hibernate.cache.region.factory_class"));
        props.put("hibernate.javax.cache.provider", env.getProperty ("hibernate.javax.cache.provider"));
        props.put("hibernate.generate_statistics", env.getProperty ("hibernate.generate_statistics"));
        sessionFactoryBean.setHibernateProperties (props);

        return sessionFactoryBean;

    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactoryBean) {
        HibernateTransactionManager txManager = new HibernateTransactionManager ();
        txManager.setSessionFactory (sessionFactoryBean);

        return txManager;
    }

}
