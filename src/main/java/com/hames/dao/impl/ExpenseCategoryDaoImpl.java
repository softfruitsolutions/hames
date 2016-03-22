package com.hames.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.ExpenseCategory;
import com.hames.dao.ExpenseCategoryDao;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class ExpenseCategoryDaoImpl extends GenericDaoImpl<ExpenseCategory> implements ExpenseCategoryDao {
	
	private static final String COLLECTION_NAME = "expense_category";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
	@Override
	public boolean isExistsByName(String categoryName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("categoryName").is(categoryName));
		return hamesDataStore.exists(query, COLLECTION_NAME);
	}

}
