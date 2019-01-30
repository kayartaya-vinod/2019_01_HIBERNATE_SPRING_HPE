package com.hpe.training.programs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hpe.training.cfg.AppConfig6;
import com.hpe.training.entity.Shipper;

public class P17_TestingJdbcTemplate {

	// this is a class confined by spring's contract
	// that knows how to convert a ResultSet into a Shipper
	static class ShipperRowMapper implements RowMapper<Shipper> {

		@Override
		public Shipper mapRow(ResultSet rs, int rowNum) throws SQLException {
			Shipper shipper = new Shipper();
			shipper.setShipperId(rs.getInt("shipper_id"));
			shipper.setCompanyName(rs.getString("company_name"));
			shipper.setPhone(rs.getString("phone"));
			return shipper;
		}

	}

	private static JdbcTemplate template;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig6.class);
		template = ctx.getBean(JdbcTemplate.class);

		// addNewShipper();
		// printProductCount();
		// printEmployeeNameForId(4); // employee_id = 4
		// printShipperData(2); // shipper_id = 2
		// printAllShipperData();
		// printShipperDataForId(2);
		printAllShippers();
		ctx.close();
	}

	static void printAllShippers() {
		RowMapper<Shipper> srm = (rs, rowNum) -> {
				Shipper shipper = new Shipper();
				shipper.setShipperId(rs.getInt("shipper_id"));
				shipper.setCompanyName(rs.getString("company_name"));
				shipper.setPhone(rs.getString("phone"));
				return shipper;
			};
		
		List<Shipper> list = template.query("select * from shippers", srm);
		
		for(Shipper s: list) {
			System.out.println(s.getCompanyName() + " --> " + s.getPhone());
		}
	}

	static void printShipperDataForId(int shipperId) {
		String sql = "select * from shippers where shipper_id = ?";
		// SQL results in 1 row multiple columns
		Shipper sh = template.queryForObject(sql, new ShipperRowMapper(), shipperId);
		System.out.println("Company name = " + sh.getCompanyName());
		System.out.println("Phone        = " + sh.getPhone());
	}

	static void printAllShipperData() {
		String sql = "select * from shippers";
		// use queryForList(..) when the SQL results in multiple rows
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> rec : list) {
			System.out.println("Company = " + rec.get("company_NAME"));
			System.out.println("Phone   = " + rec.get("phone"));
			System.out.println();
		}
	}

	static void printShipperData(int shipperId) {
		String sql = "select * from shippers where shipper_id = ?";
		// use queryForMap(..) when the SQL results in 1 row multiple columns
		Map<String, Object> rec = template.queryForMap(sql, shipperId);
		System.out.println("Company = " + rec.get("company_NAME"));
		System.out.println("Phone   = " + rec.get("phone"));
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
