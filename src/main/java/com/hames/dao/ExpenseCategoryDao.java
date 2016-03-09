package com.hames.dao;

import com.hames.bean.ExpenseCategory;
import com.hames.mongo.GenericDao;

public interface ExpenseCategoryDao extends GenericDao<ExpenseCategory> {
	
	/**
	 * Is a category exists by name
	 * @param categoryName
	 * @return
	 */
	boolean isExistsByName(String categoryName);
}
