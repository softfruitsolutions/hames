package com.hames.dao;

import java.util.List;

import com.hames.bean.ExpenseCategory;

public interface ExpenseCategoryDao {
	
	/**
	 * Save Expense Category
	 * @param expenseCategory
	 */
	void save(ExpenseCategory expenseCategory);

	/**
	 * Find Expense Category
	 * @param id
	 * @return
	 */
	ExpenseCategory findExpenseCategory(String id);
	
	/**
	 * Find all Expense Category
	 * @return
	 */
	List<ExpenseCategory> findAllExpenseCategory();

	/**
	 * Is Category Exists
	 * @param categoryId
	 * @return
	 */
	boolean isCategoryExistsById(String categoryId);
	/**
	 * Is Category Exists
	 * @param categoryName
	 * @return
	 */
	boolean isCategoryExistsByName(String categoryName);
}
