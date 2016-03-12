package com.hames.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.ExpenseCategory;
import com.hames.bean.ExpenseManager;
import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.bean.helper.UUIDHelper;
import com.hames.dao.ExpenseCategoryDao;
import com.hames.dao.ExpenseManagerDao;
import com.hames.enums.ExpenseStatus;
import com.hames.enums.PaymentItemStatus;
import com.hames.enums.PaymentItemType;
import com.hames.enums.PaymentStatus;
import com.hames.exception.ExpenseManagerException;
import com.hames.exception.ValidationException;
import com.hames.service.ExpenseManagerService;
import com.hames.service.GenericService;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.peer.BigDecimalUtil;
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
		
		ExpenseCategory ec = expenseCategoryDao.findById(expenseManager.getExpenseCategory().getCategoryId());
		if(ec == null){
			logger.debug("Expense category not found. Please contact administrator.!");
			throw new ExpenseManagerException("Expense category not found.");
		}
		expenseManager.setExpenseCategory(ec);
		
		//Setting auditable details
		expenseManager.setAuditableDetails(expenseManager.getExpenseId());
		
		//Setting Payment Details
		processPayment(expenseManager.getPayment());
		
		expenseManager.setStatus(ExpenseStatus.ACTIVE);
		
		logger.debug("Saving entity : {},{}",expenseManager.getClass(),expenseManager.toString());
		expenseManagerDao.save(expenseManager);
		logger.debug("Entity saved successfully");
		
	}
	
	private void processPayment(Payment payment){
		
		if(payment.getPaymentItems() != null){
			Iterator<PaymentItems> piIterator = payment.getPaymentItems().iterator();
			
			BigDecimal amountPaid = new BigDecimal(0);

			while(piIterator.hasNext()){
				PaymentItems item = piIterator.next();
				
				if(item.getPaymentAmount() != null && BigDecimalUtil.isGreaterThan(item.getPaymentAmount(), BigDecimal.ZERO)){
					
					//Adding Paid Amount
					amountPaid = amountPaid.add(item.getPaymentAmount());
					
					//Setting Payment Item
					if(item.getPaymentDate() == null){
						item.setPaymentDate(new DateTime());
					}
					
					item.setPaymentType(PaymentItemType.CASH);
					item.setPaymentItemStatus(PaymentItemStatus.ACTIVE_PAYMENT_ITEM);
					if(item.getDescription() == null || item.getDescription().isEmpty()){
						logger.error("Item description required");
						throw new ExpenseManagerException("Item description required");
					}
					
				}else{
					piIterator.remove();
				}
			}

			if(!BigDecimalUtil.isEqual(amountPaid, payment.getTotalAmount())){
				logger.error("Amount paid is not equal to total amount");
				throw new ExpenseManagerException("Amount paid isn't equal to total amount");
			}
			
			//Setting payment status
			payment.setPaymentStatus(PaymentStatus.PAID);
			payment.setPaymentId(UUIDHelper.getUUID());
			
		}else{
			logger.error("Payment Items required");
			throw new ExpenseManagerException("Payment items required");
		}

		
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
			boolean isExists = expenseCategoryDao.isExistsByName(expenseCategory.getCategoryName());
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
		return expenseCategoryDao.findAll();
	}

	@Override
	public ExpenseCategory getExpenseCategory(String categoryId) {
		return expenseCategoryDao.findById(categoryId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return expenseManagerDao.getPagedDatatable(request);
	}

	@Override
	public ExpenseManager getExpense(String id) {
		return expenseManagerDao.findById(id);
	}

}
