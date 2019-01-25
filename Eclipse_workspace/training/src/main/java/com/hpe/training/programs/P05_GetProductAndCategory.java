package com.hpe.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

public class P05_GetProductAndCategory {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			Session session = factory.openSession();
			
			Integer id = 23;
			// getting a product also gets the category entity corresponding to the same
			Product p1 = (Product) session.get(Product.class, id);
			
			session.close();
			
			Category c1 = p1.getCategory();
			
			// product info
			System.out.println("Product name       = " + p1.getProductName());
			System.out.println("Quantity per unit  = " + p1.getQuantityPerUnit());
			System.out.println("Price              = $ " + p1.getUnitPrice());
			
			// category info
			System.out.println("Category           = " + c1.getCategoryName());
			System.out.println("Description        = " + c1.getDescription());
			
		} finally {
			factory.close();
		}
	}

}
