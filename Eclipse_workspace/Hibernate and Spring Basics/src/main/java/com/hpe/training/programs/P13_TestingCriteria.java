package com.hpe.training.programs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P13_TestingCriteria {

	private static Session session;
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();
			
			printAllCategories();
			printProductsByPriceRange(50.0, 500.0); // minPrice=50, maxPrice=500
			printProductsByCategory("Beverages"); // categoryName = "Beverages"
			printCategoriesBySupplier("Exotic Liquids"); // Exotic Liquids --> supplier's company name
			printProductNamesAndPrices(); // example for PROJECTION
			printCategorywiseProductSummary(); // example of GROUP BY
			
			session.close();
		} finally {
			factory.close();
		}
	}

	static void printCategorywiseProductSummary() {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("category", "cat");
		
		ProjectionList projList = Projections.projectionList()
				.add(Projections.groupProperty("cat.categoryName"))
				.add(Projections.rowCount())
				.add(Projections.avg("unitPrice"));
		cr.setProjection(projList); 
		List<Object[]> list = cr.list();
		
		for(Object[] data: list) {
			System.out.printf("%s --> %d,  $%.2f\n", data[0], data[1], data[2]);
		}
		System.out.println();
	}

	static void printProductNamesAndPrices() {
		Criteria cr = session.createCriteria(Product.class);
		
		ProjectionList projList = Projections.projectionList()
				.add(Projections.property("productName"))
				.add(Projections.property("unitPrice"));
		
		cr.setProjection(projList); 
		cr.setMaxResults(10);
		
		List<Object[]> list = cr.list();
		for(Object[] data: list) {
			System.out.printf("%s --> $%.2f\n", data[0], data[1]);
		}
		System.out.println();
	}

	static void printCategoriesBySupplier(String supplierName) {
		Criteria cr = session.createCriteria(Category.class);
		cr.createAlias("products", "pr");
		cr.createAlias("pr.supplier", "sup");
		cr.add(Restrictions.eq("sup.companyName", supplierName));
		
		Set<Category> categories = new HashSet<Category>();
		categories.addAll(cr.list());
		for(Category c: categories) {
			System.out.printf("%d - %s (%s)\n", 
					c.getCategoryId(), c.getCategoryName(), c.getDescription());
		}
		System.out.println();
	}

	static void printProductsByCategory(String catName) {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("category", "cat");
		cr.add(Restrictions.eq("cat.categoryName", catName));
		List<Product> list = cr.list();
		for(Product p: list) {
			System.out.printf("%-35s $%10.2f\n", p.getProductName(), p.getUnitPrice());
		}
		System.out.println();
	}

	static void printProductsByPriceRange(double min, double max) {
		Criteria cr = session.createCriteria(Product.class);
		// cr.add(Restrictions.between("unitPrice", min, max));
		cr.add(Restrictions.ge("unitPrice", min));
		cr.add(Restrictions.le("unitPrice", max));
		List<Product> list = cr.list();
		for(Product p: list) {
			System.out.printf("%-35s $%10.2f\n", p.getProductName(), p.getUnitPrice());
		}
		System.out.println();
	}

	static void printAllCategories() {
		Criteria cr = session.createCriteria(Category.class);
		List<Category> list = cr.list();
		
		for(Category c: list) {
			System.out.printf("%d - %s (%s)\n", 
					c.getCategoryId(), c.getCategoryName(), c.getDescription());
		}
		System.out.println();
	}
}









