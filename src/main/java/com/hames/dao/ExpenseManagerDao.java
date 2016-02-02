package com.hames.dao;

import java.util.List;

import com.hames.bean.ExpenseManager;

public interface ExpenseManagerDao {

	/**
	 * Save Expense 
	 * @param expense
	 */
	void save(ExpenseManager expenseManager);
	
	/**
	 * Find all Expenses
	 * @return
	 */
	List<ExpenseManager> findAllExpenses();
}
