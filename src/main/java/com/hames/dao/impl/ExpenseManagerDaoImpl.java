package com.hames.dao.impl;

import org.springframework.stereotype.Repository;

import com.hames.bean.ExpenseManager;
import com.hames.dao.ExpenseManagerDao;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class ExpenseManagerDaoImpl extends GenericDaoImpl<ExpenseManager> implements ExpenseManagerDao {

	private static final String COLLECTION_NAME = "expense_manager";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
