package com.hpe.training.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {

	private HibernateUtil() {
	}

	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			Configuration cfg = new Configuration();
			cfg.configure();
			ServiceRegistry registry = 
					new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties())
					.build();
			factory = cfg.buildSessionFactory(registry);
		}

		return factory;
	}
}
