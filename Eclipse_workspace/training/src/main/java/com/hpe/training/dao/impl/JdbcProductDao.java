package com.hpe.training.dao.impl;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class JdbcProductDao implements ProductDao {

	@Override
	public int count() throws DaoException {
		return 100;
	}

}
