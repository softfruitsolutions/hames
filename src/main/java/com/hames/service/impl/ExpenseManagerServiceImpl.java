package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.ExpenseCategory;
import com.hames.bean.ExpenseManager;
import com.hames.bean.Payment;
import com.hames.dao.ExpenseCategoryDao;
import com.hames.dao.ExpenseManagerDao;
import com.hames.exception.ValidationException;
import com.hames.service.ExpenseManagerService;
import com.hames.service.GenericService;
import com.hames.validator.ExpenseCategoryValidator;
import com.hames.validator.ExpenseManagerValidator;
import com.hames.validator.PaymentValidator;

@Service
public class ExpenseManagerServiceImpl extends GenericService implements ExpenseManagerService{

	private static final Logger logger = LoggerFactory.getLogger(ExpenseManagerServiceImpl.class);

	@Autowired private ExpenseManagerDao expenseManagerDao;
	@Autowired private ExpenseCategoryDao expenseCategoryDao;
	
	@Override
	public Validator getValidator() {
		return new ExpenseManagerValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return ExpenseManager.class;
	}
	
	@Override
	public void saveExpense(ExpenseManager expenseManager) {
		try{
			validate(expenseManager);
			validate(expenseManager.getPayment(), new PaymentValidator(), Payment.class);
		}catch(ValidationException e){
			logger.debug(e.getMessage());
			throw new ValidationException(e.getMessage());
		}
		
		//Setting auditable details
		expenseManager.setAuditableDetails(expenseManager.getExpenseId());
		
		//Setting Payment Details
		processPayment(expenseManager.getPayment());
		
		logger.debug("Saving entity : {},{}",expenseManager.getClass(),expenseManager.toString());
		expenseManagerDao.save(expenseManager);
		logger.debug("Entity saved successfully");
		
	}
	
	private void processPayment(Payment payment){
		
	}

	@Override
	public void saveExpenseCategory(ExpenseCategory expenseCategory) {
		logger.debug("Saving Expense Category");
		try{
			validate(expenseCategory, new ExpenseCategoryValidator(), ExpenseCategory.class);
		}catch(ValidationException e){
			logger.debug(e.getMessage());
			throw new ValidationException(e.getMessage());
		}
		
		//Checking CategoryName already exists
		if(expenseCategory.getCategoryId() == null || expenseCategory.getCategoryId().isEmpty()){
			boolean isExists = expenseCategoryDao.isCategoryExistsByName(expenseCategory.getCategoryName());
			if(isExists){
				logger.debug("Expense Category already exists.");
				throw new IllegalArgumentException("Expense Category already exists.");
			}
		}
		
		//Setting Auditable details
		expenseCategory.setAuditableDetails(expenseCategory.getCategoryId());
		
		logger.debug("Saving entity : {},{}",expenseCategory.getClass(),expenseCategory.toString());
		expenseCategoryDao.save(expenseCategory);
		logger.debug("Entity saved successfully");
		
	}

	@Override
	public List<ExpenseCategory> getAllExpenseCategory() {
		return expenseCategoryDao.findAllExpenseCategory();
	}

	@Override
	public ExpenseCategory getExpenseCategory(String categoryId) {
		return expenseCategoryDao.findExpenseCategory(categoryId);
	}

}
