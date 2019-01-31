package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.utils.HibernateUtil;

public class P03_ChangeCategoryName {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.openSession();
			
			// c1 is a persistent object
			Category c1 = (Category) session.get(Category.class, 9);
			
			// changes to persistent objects make hibernate mark them as "dirty"
			// during the "commit", dirty objects result in SQL UPDATE executions
			
			c1.setCategoryName("Electronic products");
			session.beginTransaction().commit();
			
			session.close();
			
			// c1 now is a detached object
			
		} finally {
			factory.close();
		}
	}
}
