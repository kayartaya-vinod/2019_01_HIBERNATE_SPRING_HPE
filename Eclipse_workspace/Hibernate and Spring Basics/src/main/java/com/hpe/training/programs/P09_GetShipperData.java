package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Shipper;
import com.hpe.training.utils.HibernateUtil;

public class P09_GetShipperData {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.openSession();
			Shipper s1 = (Shipper) session.get(Shipper.class, 3);
			System.out.println("Name     = " + s1.getCompanyName());
			System.out.println("Phone    = " + s1.getPhone());
			
			System.out.println("No. of customers to whom orders have been delivered: " +
					s1.getCustomers().size());
			

			session.close();
		} finally {
			factory.close();
		}
	}
}
