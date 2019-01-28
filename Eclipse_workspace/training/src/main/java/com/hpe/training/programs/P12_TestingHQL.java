package com.hpe.training.programs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
			printProductsByPage(4); // 4 --> pageNum (assuming 10 is the page size)
			printProductNamesAndPrices(); // example for PROJECTION
			printCategorywiseProductSummary(); // example of GROUP BY
			incrementProductPriceBy(1.0); // inc price is $1
			
			session.close();
		} finally {
			factory.close();
		}
	}

	static void incrementProductPriceBy(double incPrice) {
		Transaction tx = session.beginTransaction();
		String hql = "update Product set unitPrice = unitPrice + :INC_PRICE";
		Query qry = session.createQuery(hql);
		qry.setDouble("INC_PRICE", incPrice);
		try {
			qry.executeUpdate(); // used for DML UPDATE/DELETE (bulk operations)
			tx.commit();
			System.out.printf("Product price icreased by $%.2f for each product\n", incPrice);
		} catch (Exception e) {
			tx.rollback();
			System.out.println("OOPS! Something went wrong! -- " + e.getMessage());
		}
		
	}

	static void printCategorywiseProductSummary() {
		String hql = "select p.category.categoryName, count(p), avg(p.unitPrice), "
				+ "sum(p.unitPrice * p.unitsInStock) from Product p "
				+ "group by p.category.categoryName "
				+ "having count(p) > 10 "
				+ "order by p.category.categoryName ";
				
		Query qry = session.createQuery(hql);
		List<Object[]> list = qry.list();
		for(Object[] data: list) {
			String catName = (String) data[0];
			Long count = (Long) data[1];
			Double avgPrice = (Double) data[2];
			Double stockValue = (Double) data[3];
			System.out.printf("%s (count=%d, avg=%.2f, stock_val=%.2f)\n",
					catName, count, avgPrice, stockValue);
		}
		System.out.println();
	}

	static void printProductNamesAndPrices() {
		String hql = "select productName, unitPrice from Product";
		Query qry = session.createQuery(hql);
		qry.setMaxResults(10); // limit the no.of records to fetch
		
		List<Object[]> list = qry.list();
		for(Object[] data: list) {
			String pname = (String) data[0];
			Double price = (Double) data[1];
			System.out.println(pname + " --> $" + price);
		}
		System.out.println();
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		Query qry = session.createQuery("from Product");
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);
		
		List<Product> list = qry.list();
		for (Product p : list) {
			System.out.printf("%2d %-40s $%10.2f\n",
					p.getProductId(),
					p.getProductName(),
					p.getUnitPrice());
		}
		System.out.println();
	}

	// example for joining a OneToMany or ManyToMany member
	static void printCategoriesBySupplier(String companyName) {
		String hql = "select c from Category c join c.products p where p.supplier.companyName = :COMPANY_NAME";
		Query qry = session.createQuery(hql);
		qry.setString("COMPANY_NAME", companyName);
		Set<Category> categories = new HashSet<Category>();
		categories.addAll(qry.list());

		System.out.printf("Categories supplied by %s are\n", companyName);
		for (Category c : categories) {
			System.out.println(c.getCategoryName());
		}
		System.out.println();
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
