package com.hpe.training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class JdbcProductDao implements ProductDao {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	private DataSource dataSource;

	public JdbcProductDao() {
		// System.out.println("JdbcProductDao instantiated!");
	}

	public JdbcProductDao(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	// a setter is a.k.a a writable property (mutator)
	// setXyz --> property "xyz"

	// this function is a writable property called "driverClassName"
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// writable property called "dataSource"
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	private Connection openConnection() throws ClassNotFoundException, SQLException {
		// if the connection pool exists, use a connection from the same
		if (dataSource != null) {
			return dataSource.getConnection();
		}

		// if the pool doesn't exist, then try to create a new connection
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public int count() throws DaoException {
		
		String sql = "select count(*) from products";
		try(
			Connection conn = openConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		) {
			rs.next();
			return rs.getInt(1);
		}
		catch(Exception ex) {
			throw new DaoException(ex);
		}
		
	}

}









