package com.hames.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.ExpenseCategory;
import com.hames.dao.ExpenseCategoryDao;
import com.hames.service.ExpenseCategoryService;
import com.hames.service.GenericServiceImpl;
import com.hames.validator.ExpenseCategoryValidator;

@Service
public class ExpenseCategoryServiceImpl extends GenericServiceImpl<ExpenseCategory> implements ExpenseCategoryService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseCategoryServiceImpl.class);
	
	@Autowired
	private ExpenseCategoryDao expenseCategoryDao;
	
	@Override
	public Validator getValidator() {
		return new ExpenseCategoryValidator();
	}

	@Override
	public String save(ExpenseCategory expenseCategory) {
		//Validating expense category
		validate(expenseCategory);
		
		//Checking CategoryName already exists
		if(expenseCategory.getCategoryId() == null || expenseCategory.getCategoryId().isEmpty()){
			if(expenseCategoryDao.isExistsByName(expenseCategory.getCategoryName())){
				LOGGER.debug("Expense Category already exists.");
				throw new IllegalArgumentException("Expense Category already exists.");
			}
		}
		
		//Setting Auditable details
		expenseCategory.setAudit(expenseCategory.getCategoryId());
		
		return super.save(expenseCategory);
	}
}
