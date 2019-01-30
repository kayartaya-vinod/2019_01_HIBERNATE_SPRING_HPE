package com.hpe.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hpe.training.dao.ProductDao;
import com.hpe.training.dao.impl.JdbcProductDao;

@Configuration
public class AppConfig4 {

	@Bean
	public ProductDao jdbc(@Qualifier("mySqlDbcp") DataSource ds) { // dependency injection
		JdbcProductDao jpd = new JdbcProductDao();
		jpd.setDataSource(ds); // dependency injection 
		return jpd;
	}

	@Bean
	public BasicDataSource h2Dbcp() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/2019_01_HIBERNATE_SPRING_HPE");
		bds.setUsername("sa");
		bds.setPassword("");
		return bds;
	}

	@Bean
	public BasicDataSource mySqlDbcp() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost/northwind");
		bds.setUsername("root");
		bds.setPassword("root");
		return bds;
	}
}
