package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.utils.HibernateUtil;

public class P04_DifferenceBetweenUpdateAndMerge {
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.openSession();
			// c1 is a persistent object
			Category c1 = (Category) session.get(Category.class, 9);
			session.close();
			
			// c1 now is a detached object
			
			// new session; empty
			session = factory.openSession();
			// to make "c1" part of the new session
			// we can use - update, saveOrUpdate or merge
			session.merge(c1);
			session.beginTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
