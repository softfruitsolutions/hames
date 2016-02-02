package com.hames.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hames.bean.ExpenseManager;
import com.hames.bean.helper.UUIDHelper;
import com.hames.dao.ExpenseManagerDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;

@Repository
public class ExpenseManagerDaoImpl extends GenericDao implements ExpenseManagerDao {

	private static final String COLLECTION_NAME = "expense_manager";

	@Autowired private HamesDataStore hamesDataStore;
	
	@Override
	public Class<?> getEntityClass() {
		return ExpenseManager.class;
	}
	
	@PostConstruct
	public void createCollection() {
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
		}
	}
	
	@Override
	public void save(ExpenseManager expenseManager) {
		if(!hamesDataStore.exists(expenseManager.getExpenseId(),COLLECTION_NAME)){
			expenseManager.setExpenseId(UUIDHelper.getUUID());	
		}
		hamesDataStore.save(expenseManager,COLLECTION_NAME);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ExpenseManager> findAllExpenses() {
		return (List<ExpenseManager>) hamesDataStore.findAll(getEntityClass(), COLLECTION_NAME);
	}

}
