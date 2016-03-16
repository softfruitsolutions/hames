package com.hames.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;

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
import com.hames.enums.ExpenseStatus;
import com.hames.enums.PaymentItemStatus;
import com.hames.enums.PaymentItemType;
import com.hames.enums.PaymentStatus;
import com.hames.exception.ExpenseManagerException;
import com.hames.service.ExpenseManagerService;
import com.hames.service.GenericServiceImpl;
import com.hames.util.peer.BigDecimalUtil;
import com.hames.validator.ExpenseManagerValidator;

@Service
public class ExpenseManagerServiceImpl extends GenericServiceImpl<ExpenseManager> implements ExpenseManagerService{

	private static final Logger logger = LoggerFactory.getLogger(ExpenseManagerServiceImpl.class);

	@Autowired
	private ExpenseCategoryDao expenseCategoryDao;
	
	@Override
	public Validator getValidator() {
		return new ExpenseManagerValidator();
	}

	@Override
	public String save(ExpenseManager expenseManager) {
		//Validating Expense Manager
		validate(expenseManager);
		
		ExpenseCategory ec = expenseCategoryDao.findById(expenseManager.getExpenseCategory().getCategoryId());
		if(ec == null){
			logger.debug("Expense category not found. Please contact administrator.!");
			throw new ExpenseManagerException("Expense category not found.");
		}
		expenseManager.setExpenseCategory(ec);
		expenseManager.setAuditableDetails(expenseManager.getExpenseId());
		expenseManager.setStatus(ExpenseStatus.ACTIVE);
		
		//Setting Payment Details
		processPayment(expenseManager.getPayment());
		
		return super.save(expenseManager);
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

}
