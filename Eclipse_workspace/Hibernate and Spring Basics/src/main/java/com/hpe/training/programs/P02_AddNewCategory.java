package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hpe.training.entity.Category;
import com.hpe.training.utils.HibernateUtil;

public class P02_AddNewCategory {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Category c = new Category("Electronics", "TV, Radio, Music system etc");
			
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			try {
				session.persist(c);
				tx.commit();
				System.out.println("Category data saved!");
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}
			
		} finally {
			factory.close();
		}
	}
}
