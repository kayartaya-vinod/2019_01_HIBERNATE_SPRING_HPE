package com.hpe.training.cfg;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.hpe.training.dao.impl" })
public class AppConfig5 {

	@Bean(name = { "dataSource" })
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
