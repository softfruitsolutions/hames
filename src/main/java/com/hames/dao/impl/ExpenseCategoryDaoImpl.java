package com.hames.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.ExpenseCategory;
import com.hames.bean.helper.UUIDHelper;
import com.hames.dao.ExpenseCategoryDao;
import com.hames.mongo.GenericDao;
import com.hames.mongo.HamesDataStore;

@Repository
public class ExpenseCategoryDaoImpl extends GenericDao implements ExpenseCategoryDao {
	
	private static final String COLLECTION_NAME = "expense_category";
	
	@Autowired private HamesDataStore hamesDataStore;
	
	@Override
	public Class<?> getEntityClass() {
		return ExpenseCategory.class;
	}
	
	@PostConstruct
	public void createCollection() {
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
		}
	}
	
	@Override
	public void save(ExpenseCategory expenseCategory) {
		if(!hamesDataStore.exists(expenseCategory.getCategoryId(),COLLECTION_NAME)){
			expenseCategory.setCategoryId(UUIDHelper.getUUID());	
		}
		hamesDataStore.save(expenseCategory,COLLECTION_NAME);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ExpenseCategory> findAllExpenseCategory() {
		return (List<ExpenseCategory>) hamesDataStore.findAll(getEntityClass(), COLLECTION_NAME);
	}

	@Override
	public boolean isCategoryExistsById(String categoryId) {
		return hamesDataStore.exists(categoryId, COLLECTION_NAME);
	}

	@Override
	public boolean isCategoryExistsByName(String categoryName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("categoryName").is(categoryName));
		return hamesDataStore.exists(query, COLLECTION_NAME);
	}

	@Override
	public ExpenseCategory findExpenseCategory(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("categoryId").is(id));
		return (ExpenseCategory) hamesDataStore.findOne(query, getEntityClass(), COLLECTION_NAME);
	}

}
