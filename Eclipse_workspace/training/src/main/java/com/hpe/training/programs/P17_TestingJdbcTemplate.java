package com.hpe.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hpe.training.cfg.AppConfig6;

public class P17_TestingJdbcTemplate {

	private static JdbcTemplate template;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig6.class);
		template = ctx.getBean(JdbcTemplate.class);
		
		// addNewShipper();
		// printProductCount();
		printEmployeeNameForId(4); // employee_id=4
		
		ctx.close();
	}

	static void printEmployeeNameForId(int employeeId) {
		String sql = "select first_name || ' ' || last_name from employees where employee_id = ?";
		String name = template.queryForObject(sql, String.class, employeeId);
		System.out.println("Hello, " + name);
	}

	static void printProductCount() {
		String sql = "select count(*) from products";
		// use queryForObject(..) when the SQL results in 1 row 1 column
		int pc = template.queryForObject(sql, Integer.class);
		System.out.println("There are " + pc + " products.");
	}

	static void addNewShipper() {
		String sql = "insert into shippers values(?, ?, ?)";
		// use the "update(..)" function for SQL DML statements (INSERT/UPDATE/DELETE)
		template.update(sql, 4, "KVinod Transports", "9731424784");
		System.out.println("New shipper added!");
	}
}






