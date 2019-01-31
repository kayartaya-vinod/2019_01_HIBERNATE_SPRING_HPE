package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.LineItem;
import com.hpe.training.utils.HibernateUtil;

public class P11_GetLineItems {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		try {
			Session session = factory.openSession();
			
			// an instance of LineItem is used for representing a primary key value
			LineItem id = new LineItem(10248, 11);
			
			LineItem item = (LineItem) session.get(LineItem.class, id);
			session.close();
			
			System.out.println("Unit price = $" + item.getUnitPrice());
			System.out.println("Discount   = " + (item.getDiscount() * 100) + "%");
			System.out.println("Quantity   = " + item.getQuantity());
			

			
		} finally {
			factory.close();
		}
	}
}
