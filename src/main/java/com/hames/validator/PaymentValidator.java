package com.hames.validator;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.util.BigDecimalUtil;

public class PaymentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Payment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Payment payment = (Payment) target;
		
		if(payment.getTotalAmount() == null || BigDecimalUtil.isEqual(payment.getTotalAmount(), BigDecimal.ZERO)){
			errors.rejectValue("totalAmount", "", "Total Amount Required");
			return;
		}
		
		if(payment.getTotalAmount() != null && BigDecimalUtil.isLessThan(payment.getTotalAmount(), BigDecimal.ZERO)){
			errors.rejectValue("totalAmount", "","Total Amount can't be negative value");
			return;
		}
		
		if(payment.getDiscountAmount() != null && BigDecimalUtil.isLessThan(payment.getDiscountAmount(), BigDecimal.ZERO)){
			errors.rejectValue("discountAmount", "", "Discount Amount can't be negative value");
			return;
		}
		
		if(payment.getDiscountAmount() != null && BigDecimalUtil.isGreaterThan(payment.getDiscountAmount(), payment.getTotalAmount())){
			errors.rejectValue("discountAmount", "", "Discount Amount can't be greater than total amount");
			return;
		}
		
		if(payment.getPaymentItems() != null){
			BigDecimal amountPaid = new BigDecimal(0);
			for (PaymentItems item : payment.getPaymentItems()) {

				if(BigDecimalUtil.isLessThan(item.getPaymentAmount(), BigDecimal.ZERO)){
					errors.rejectValue("paymentAmount", "","Payment amount can't be negative value");
					return;
				}
				
				if(item.getPaymentAmount() != null && BigDecimalUtil.isGreaterThan(item.getPaymentAmount(), BigDecimal.ZERO)){
					amountPaid = amountPaid.add(item.getPaymentAmount());
				}
			}
			
			if(BigDecimalUtil.isGreaterThan(amountPaid, payment.getTotalAmount())){
				errors.rejectValue("paymentAmount", "", "Payment Amount can't be greater than total amount");
				return;
			}
			
		}
		
	}	
}
