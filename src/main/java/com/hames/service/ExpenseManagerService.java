package com.hames.service;

import java.util.List;

import com.hames.bean.ExpenseCategory;
import com.hames.bean.ExpenseManager;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface ExpenseManagerService {

	/**
	 * Save Expense Manager
	 * @param expenseManager
	 */
	public void saveExpense(ExpenseManager expenseManager);
	
	/**
	 * Get Expense from Expense Manager
	 * @param id
	 * @return
	 */
	public ExpenseManager getExpense(String id);
	
	/**
	 * Datatable Request
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	
	//Expense Category
	/**
	 * Expense Category
	 * @param expenseCategory
	 */
	public void saveExpenseCategory(ExpenseCategory expenseCategory);
	
	/**
	 * Get Expense Category by Id
	 * @param categoryId
	 */
	public ExpenseCategory getExpenseCategory(String categoryId);
	
	/**
	 * Get All Expense Category
	 * @return
	 */
	public List<ExpenseCategory> getAllExpenseCategory();
	
}
