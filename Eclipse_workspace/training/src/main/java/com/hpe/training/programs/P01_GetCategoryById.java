package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;

public class P01_GetCategoryById {

	public static void main(String[] args) {

		// empty configuration object
		Configuration cfg = new Configuration();

		// load information from hibernate.cfg.xml
		cfg.configure();

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		// build the session factory (represents a db session, singleton)
		SessionFactory factory = cfg.buildSessionFactory(registry);

		// get a session (represents a live db connection)
		Session session = factory.openSession();

		// get an instanceof Category from the db
		// C --> save(), persist(), saveOrUpdate(), merge()
		// R --> get(), load()
		// U --> update(), merge(), saveOrUpdate()
		// D --> delete()

		Integer id = 2;
		// System.out.println("pass-1");
		Category c = (Category) session.get(Category.class, id);
		c = (Category) session.get(Category.class, id);

		// session.refresh(c);
		//
		// c = (Category) session.get(Category.class, id);
		// c = (Category) session.get(Category.class, id);
		// System.out.println("pass-2");

		// System.out.println("Got an instanceof " + c.getClass().getName());

		System.out.println("Name        = " + c.getCategoryName());
		System.out.println("Description = " + c.getDescription());

		System.out.println("Products in this category: ");
		for (Product p : c.getProducts()) {
			System.out.println(p.getProductName() + ", $ " + p.getUnitPrice());
		}

		session.close();
		factory.close();

	}
}
