package com.hpe.training.programs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Category;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P12_TestingHQL {

	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			session = factory.openSession();

			printAllCategories();
			printProductsByPriceRange(50.0, 500.0); // minPrice=50, maxPrice=500
			printProductsByCategory("Beverages"); // categoryName = "Beverages"
			printCategoriesBySupplier("Exotic Liquids"); // Exotic Liquids --> supplier's company name
			session.close();
		} finally {
			factory.close();
		}
	}

	// example for joining a OneToMany or ManyToMany member
	static void printCategoriesBySupplier(String companyName) {
		String hql = "select c from Category c join c.products p where p.supplier.companyName = ?";
		Query qry = session.createQuery(hql);
		qry.setString(0, companyName);
		Set<Category> categories = new HashSet<Category>();
		categories.addAll(qry.list());

		System.out.printf("Categories supplied by %s are\n", companyName);
		for (Category c : categories) {
			System.out.println(c.getCategoryName());
		}
	}

	// example for joining a ManyToOne or OneToOne member
	static void printProductsByCategory(String categoryName) {
		// from Product where supplier.contactDetails.city = ?

		String hql = "from Product where category.categoryName = :CAT_NAME";
		Query qry = session.createQuery(hql);
		qry.setString("CAT_NAME", categoryName);
		List<Product> list = qry.list();
		System.out.printf("Products in %s category are: \n", categoryName);
		for (Product p : list) {
			System.out.println(p.getProductName() + " --> $" + p.getUnitPrice());
		}
		System.out.println();
	}

	static void printProductsByPriceRange(double minPrice, double maxPrice) {
		// String hql = "from Product where unitPrice between ? and ?";
		String hql = "from Product where unitPrice between :MIN and :MAX";
		Query qry = session.createQuery(hql);
		qry.setDouble("MIN", minPrice);
		qry.setDouble("MAX", maxPrice);
		List<Product> list = qry.list();
		System.out.printf("Products priced between $%.2f and $%.2f are\n", minPrice, maxPrice);
		for (Product p : list) {
			System.out.println(p.getProductName() + " --> $" + p.getUnitPrice());
		}
		System.out.println();
	}

	static void printAllCategories() {
		// String hql = "SELECT c FROM Category c";
		String hql = "from Category order by categoryName desc";
		Query qry = session.createQuery(hql);

		List<Category> list = qry.list(); // HQL is converted to SQL and is executed here
		for (Category c : list) {
			System.out.println(c.getCategoryName());
		}
		System.out.println();
	}

}
