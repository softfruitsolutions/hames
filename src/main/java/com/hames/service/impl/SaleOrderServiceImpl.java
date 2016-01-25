package com.hames.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Validator;

import com.hames.bean.Order;
import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.bean.SaleOrder;
import com.hames.dao.SaleOrderDao;
import com.hames.enums.PaymentItemStatus;
import com.hames.enums.PaymentItemType;
import com.hames.enums.PaymentStatus;
import com.hames.enums.SaleOrderStatus;
import com.hames.exception.OrderException;
import com.hames.exception.PaymentException;
import com.hames.exception.ValidationException;
import com.hames.service.SaleOrderService;
import com.hames.util.BigDecimalUtil;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.validator.PaymentValidator;
import com.hames.validator.SaleOrderValidator;

@Repository
public class SaleOrderServiceImpl extends OrderServiceImpl implements SaleOrderService {

	private static final Logger logger = LoggerFactory.getLogger(SaleOrderServiceImpl.class);
	
	@Autowired
	private SaleOrderDao saleOrderDao;

	@Override
	public Validator getValidator() {
		return new SaleOrderValidator();
	}
	
	@Override
	public Class<?> getEntityClass() {
		return SaleOrder.class;
	}
	
	@Override
	public void saveOrder(Order order) {
	
		SaleOrder saleOrder = (SaleOrder) order;
		
		try{
			validate(saleOrder);
			validate(saleOrder.getPayment(), new PaymentValidator(), Payment.class);
		}catch(ValidationException e){
			logger.debug("Validation exceptions are present");
			throw new ValidationException();
		}
		
		/**
		 * Creating Order
		 */
		createOrder(saleOrder);
		
		super.saveOrder(order);
	}
	
	private void createOrder(SaleOrder saleOrder){
		
		if(saleOrder.getSaleOrderStatus() == SaleOrderStatus.DRAFT){
			
			/**
			 * Checking Order Job No already exists
			 */
			Boolean isJobNoExists = saleOrderDao.isJobNoExists(saleOrder.getJobNo());
			if(isJobNoExists){
				throw new OrderException("Job No already exists");
			}
			
			/**
			 * Setting Payment Details
			 */
			Payment payment = saleOrder.getPayment();
			if(payment != null){
				BigDecimal amountPaid = new BigDecimal(0);
				
				if(payment.getPaymentItems() != null){
					for (PaymentItems item : payment.getPaymentItems()) {
						
						if(item.getPaymentAmount() != null && BigDecimalUtil.isGreaterThan(item.getPaymentAmount(), BigDecimal.ZERO)){
							//Adding Paid Amount
							amountPaid = amountPaid.add(item.getPaymentAmount());
							
							//Setting Payment Item
							item.setPaymentDate(new DateTime());
							item.setPaymentType(PaymentItemType.CASH);
							item.setPaymentItemStatus(PaymentItemStatus.ACTIVE_PAYMENT_ITEM);
							item.setDescription("An amount of "+item.getPaymentAmount()+" is paid to the Sale Order.");
						}
					}
					
				}
				
				/**
				 * Calculating Balance Due
				 */
				BigDecimal balanceDue = new BigDecimal(0);			//balanceDue = totalAmount - amountPaid - discount
				balanceDue = balanceDue.add(payment.getTotalAmount());
				if(BigDecimalUtil.isGreaterThan(payment.getDiscountAmount(), BigDecimal.ZERO)){
					balanceDue = balanceDue.subtract(payment.getDiscountAmount());
				}
				
				if(BigDecimalUtil.isGreaterThan(amountPaid, balanceDue)){
					throw new PaymentException("Payment Amount can't be greater than balance due.");
				}
				if(BigDecimalUtil.isGreaterThan(amountPaid, BigDecimal.ZERO)){
					balanceDue = balanceDue.subtract(amountPaid);
				}
				
				payment.setBalanceDue(balanceDue);
				payment.setAmountPaid(amountPaid);
			
				
				/**
				 * Setting Payment Status
				 */
				if(BigDecimalUtil.isEqual(payment.getAmountPaid(),BigDecimal.ZERO)){
					payment.setPaymentStatus(PaymentStatus.PENDING);
				}else if(BigDecimalUtil.isEqual(payment.getBalanceDue(), BigDecimal.ZERO)){
					payment.setPaymentStatus(PaymentStatus.PAID);
				}else if(BigDecimalUtil.isGreaterThan(payment.getAmountPaid(), BigDecimal.ZERO)){
					payment.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
				}
				
				/**
				 * Checking Payment Item exists
				 */
				if(BigDecimalUtil.isEqual(payment.getAmountPaid(),BigDecimal.ZERO)){
					payment.setPaymentItems(null);
				}
				saleOrder.setPayment(payment);
			}else{
				throw new OrderException("Order Payment Required");
			}
			
			/**
			 * Setting Sale Order Status to created
			 */
			saleOrder.setSaleOrderStatus(SaleOrderStatus.CREATED);
		}
	}

	@Override
	public void updateOrderStatus(String orderId,SaleOrderStatus saleOrderStatus) {
		logger.debug("Updating Sale Order status for Order ID : {}",orderId);
		logger.debug("Checking whether Order exists");
		Boolean orderExists = saleOrderDao.orderExists(orderId);
		if(!orderExists){
			logger.error("Sale Order doesn't exists. Please contact Administrator");
			throw new OrderException("Sale Order doesn't exists");
		}
		
		if(EnumUtils.isValidEnum(SaleOrderStatus.class, saleOrderStatus.getValue())){
			logger.debug("Fetching sale order with id :{}",orderId);
			SaleOrder saleOrder = saleOrderDao.findByOrderId(orderId);
			logger.debug("Setting status to sale order");
			saleOrder.setSaleOrderStatus(saleOrderStatus);
			saleOrderDao.save(saleOrder);
			logger.debug("Sale order status updated successfully");
		}else{
			logger.error("Invalid Sale Order Status. Opeartion Aborted..!");
			throw new OrderException("Invalid Sale order status.");
		}
	}

}
