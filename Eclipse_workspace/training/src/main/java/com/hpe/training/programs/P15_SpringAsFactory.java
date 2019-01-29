package com.hpe.training.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class P15_SpringAsFactory {
	
	public static void main(String[] args) throws DaoException {
		// a variable representing the spring container
		ClassPathXmlApplicationContext ctx;
		
		// an object of spring container
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		
		ProductDao dao = ctx.getBean(ProductDao.class);
		int pc = dao.count();
		System.out.println("There are " + pc + " products.");
		
		ctx.close();
	}

}
