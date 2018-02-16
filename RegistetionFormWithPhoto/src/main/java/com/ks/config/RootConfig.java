package com.ks.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.ks.entity.EmployeeEntityPhoto;

@Configuration
@ComponentScan(basePackages="com.ks.dao,com.ks.service")
public class RootConfig {

	public DataSource getDataSource(){
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("system");
		dataSource.setPassword("manager");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean createSessionFactory(){
		LocalSessionFactoryBean bean=new LocalSessionFactoryBean();
		bean.setHibernateProperties(getProperty());
		bean.setAnnotatedClasses(EmployeeEntityPhoto.class);
		bean.setDataSource(getDataSource());
		return bean;
	}
	public Properties getProperty(){
		Properties properties=new Properties();
		properties.put("show_sql", "true");
		properties.put("format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
}
