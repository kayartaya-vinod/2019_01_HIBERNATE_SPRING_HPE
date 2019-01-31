package com.hpe.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.training.cfg.AppConfig7;
import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

public class P18_TestProductDaoOperations {
	
	public static void main(String[] args) throws DaoException {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig7.class);
		
		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());
		
		int pc = dao.count();
		System.out.println("There are " + pc + " products.");
		
		Product p = dao.get(1);
		System.out.println("Product name = " + p.getProductName());
		System.out.println("Category     = " + p.getCategory().getCategoryName());
		System.out.println("Supplied by  = " + p.getSupplier().getCompanyName());
		
		try {
			System.out.println("Before update price = " + p.getUnitPrice());
			p.setUnitPrice(p.getUnitPrice() + 1);
			dao.update(p);
			p = dao.get(1);
			System.out.println("After update price = " + p.getUnitPrice());
		} catch (DaoException e) {
			System.out.println("Got an exception of type: " + e.getClass());
			System.err.println("There was an exception while updating the product price.");
		}
		
		List<Product> list = dao.getAll();
		System.out.println("There are " + list.size() + " products.");
		
		list = dao.getProductsByPrice(50.0, 500.0);
		System.out.println("There are " + list.size() + " products between $50 and $500.");
		

		list = dao.getProductsByPrice(500.0, 50.0);
		System.out.println("There are " + list.size() + " products between $500 and $50.");
		
		
		list = dao.getProductsNotInStock();
		System.out.println("There are " + list.size() + " products out of stock.");
		
		list = dao.getDiscontinuedProducts();
		System.out.println("There are " + list.size() + " products which are discontinued.");
		
		ctx.close();
		
	}

}





