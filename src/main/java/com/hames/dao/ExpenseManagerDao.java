package com.hames.dao;

import java.util.List;

import com.hames.bean.ExpenseManager;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

public interface ExpenseManagerDao {

	/**
	 * Save Expense 
	 * @param expense
	 */
	void save(ExpenseManager expenseManager);
	
	/**
	 * Find Expense Id
	 * @param id
	 * @return
	 */
	ExpenseManager findExpenseById(String id);
	
	/**
	 * Find all Expenses
	 * @return
	 */
	List<ExpenseManager> findAllExpenses();
	
	/**
	 * Build Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);
}
