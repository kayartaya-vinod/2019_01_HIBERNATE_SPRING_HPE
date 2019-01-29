package com.hpe.training.dao.impl;

import com.hpe.training.dao.DaoException;
import com.hpe.training.dao.ProductDao;

public class CsvProductDao implements ProductDao {
	
	public CsvProductDao() {
		// System.out.println("CsvProductDao instantiated!");
	}

	@Override
	public int count() throws DaoException {
		return 99;
	}

}
