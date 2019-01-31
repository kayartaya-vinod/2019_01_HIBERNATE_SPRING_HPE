package com.hpe.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;

@EnableTransactionManagement // creates a proxy for managing transaction
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.hpe.training.web", "com.hpe.training.dao", "com.hpe.training.aop" })
@Configuration
public class AppConfig {
	
	// ViewResolver (optional)
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}

	// Transactions via HibernateTemplate are managed by HibernateTransactionManager
	// Transactions via JdbcTemplate are managed by DataSourceTransactionManager

	// HibernateTransactionManager depends on SessionFactory
	@Bean
	public HibernateTransactionManager txManager(SessionFactory sf) { // dependency injection
		return new HibernateTransactionManager(sf); // manual wiring
	}

	@Bean(name = "dataSource")
	public BasicDataSource h2Dbcp() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/2019_01_HIBERNATE_SPRING_HPE");
		bds.setUsername("sa");
		bds.setPassword("");
		return bds;
	}

	@Bean
	public HibernateTemplate ht(SessionFactory sf) {
		return new HibernateTemplate(sf);
	}

	// This bean is NOT an instanceof SessionFactory, but has a method called
	// getObject() that returns an instanceof SessionFactory.
	// Whenever spring has to inject an object of SessionFactory, it will call
	// this bean's getObject() function.
	@Bean
	public LocalSessionFactoryBean lsfb(DataSource ds) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(ds);
		sf.setConfigLocation(new ClassPathResource("spring-hibernate.cfg.xml"));

		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		sf.setHibernateProperties(props);

		sf.setAnnotatedClasses(Product.class, Category.class);
		return sf;
	}

}
