package com.hpe.training.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;
import com.hpe.training.entity.Product;

// the name of this bean is "jdbcTemplateProductDao"
@Repository
public class JdbcTemplateProductDao implements ProductDao {

	// required=false indicate that spring should do DI only if matching bean is found
	@Autowired(required = false)
	private JdbcTemplate template;
	
	private RowMapper<Product> prm = (rs, index) -> {
		Product p = new Product();
		p.setProductId(rs.getInt("product_id"));
		p.setProductName(rs.getString("product_name"));
		p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
		p.setUnitPrice(rs.getDouble("unit_price"));
		p.setUnitsInStock(rs.getInt("units_in_stock"));
		p.setUnitsOnOrder(rs.getInt("units_on_order"));
		p.setReorderLevel(rs.getInt("reorder_level"));
		p.setDiscontinued(rs.getInt("discontinued"));
		return p;
	};

	@Override
	public void add(Product product) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public Product get(Integer productId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product product) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer productId) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public int count() throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPrice(Double min, Double max) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
