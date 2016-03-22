package com.hames.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.bean.SaleOrder;
import com.hames.bean.Staff;
import com.hames.bean.helper.UUIDHelper;
import com.hames.dao.CustomerDao;
import com.hames.dao.SaleOrderDao;
import com.hames.enums.PaymentItemStatus;
import com.hames.enums.PaymentItemType;
import com.hames.enums.PaymentStatus;
import com.hames.enums.SaleOrderStatus;
import com.hames.exception.OrderException;
import com.hames.exception.PaymentException;
import com.hames.exception.StaffException;
import com.hames.mongo.Sequence;
import com.hames.mongo.SequenceDao;
import com.hames.order.model.SaleOrderReport;
import com.hames.order.model.SaleOrderSearchCriteria;
import com.hames.party.model.Customer;
import com.hames.report.ReportRequest;
import com.hames.report.ReportResponse;
import com.hames.service.SaleOrderService;
import com.hames.service.StaffService;
import com.hames.util.peer.BigDecimalUtil;
import com.hames.validator.PaymentValidator;
import com.hames.validator.SaleOrderValidator;

@Repository
public class SaleOrderServiceImpl extends OrderServiceImpl<SaleOrder> implements SaleOrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaleOrderServiceImpl.class);
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SaleOrderDao saleOrderDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	private StaffService staffService;

	@Override
	public Validator getValidator() {
		return new SaleOrderValidator();
	}
	
	@Override
	@Transactional
	public String save(SaleOrder saleOrder) {
	
		//Validating
		validate(saleOrder);
		validate(saleOrder.getPayment(), new PaymentValidator());
		
		//Creating or updating order based on orderId
		if(saleOrder.getOrderId() == null || saleOrder.getOrderId().isEmpty()){
			createOrder(saleOrder);
		}else{
			updateOrder(saleOrder);
		}
		
		//Setting auditable details
		saleOrder.setAudit(saleOrder.getOrderId());
		
		return super.save(saleOrder);
	}
	
	private void createOrder(SaleOrder saleOrder){
		
		if(saleOrder.getSaleOrderStatus() == SaleOrderStatus.DRAFT){
			
			LOGGER.debug("Checking sale order job no exists : {}",saleOrder.getJobNo());
			// Checking Order Job No already exists
			if(saleOrderDao.isJobNoExists(saleOrder.getJobNo())){
				LOGGER.debug("Job no already exists. Opeartion aborted.!");
				throw new OrderException("Job No already exists");
			}
			
			if(!staffService.isExists(saleOrder.getStaffConcerned())){
				LOGGER.debug("Staff not found. Opeartion aborted.");
				throw new StaffException("Staff not found");
			}
			
			// Process Payment 
			processPayment(saleOrder);
			
			// Setting Sale Order Status to created
			saleOrder.setSaleOrderStatus(SaleOrderStatus.CREATED);
			
			// Setting and Updating JobNo
			saleOrder.setJobNo(getNextJobNo());
			sequenceDao.updateSequence(Sequence.SALE_ORDER_SEQUENCE);
		}
	}

	public void updateOrder(SaleOrder saleOrder){
		
		SaleOrder so =  saleOrderDao.findById(saleOrder.getOrderId());
		if(so == null){
			LOGGER.error("Sale Order doesn't exists. Please contact Administrator");
			throw new OrderException("Sale Order doesn't exists");
		}
		
		/**
		 * Checking Status
		 */
		if(so.getSaleOrderStatus() == SaleOrderStatus.DELIVERED){
			if(saleOrder.getSaleOrderStatus() != so.getSaleOrderStatus()){
				LOGGER.debug("Order already delivered. Operation Aborted.");
				throw new OrderException("Order already delivered can't change the status.!");
			}
		}
		
		//Process Payment
		processPayment(saleOrder);
	}
	
	public void processPayment(SaleOrder saleOrder){
		
		/**
		 * Setting Payment Details
		 */
		Payment payment = saleOrder.getPayment();
		if(payment != null && payment.getPaymentStatus() != PaymentStatus.PAID){
			
			if(payment.getPaymentId() == null || payment.getPaymentId().isEmpty()){
				payment.setPaymentId(UUIDHelper.getUUID());
				payment.setPaymentDate(saleOrder.getOrderDate());
			}
			
			BigDecimal amountPaid = new BigDecimal(0);
			
			if(payment.getPaymentItems() != null){
				Iterator<PaymentItems> piIterator = payment.getPaymentItems().iterator();
				
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
							item.setDescription("An amount of "+item.getPaymentAmount()+" is paid to the Sale Order.");
						}
					}else if(item.getPaymentAmount() ==  null){
						piIterator.remove();
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
		
	}

	@Override
	public void updateOrderStatus(String orderId,SaleOrderStatus saleOrderStatus) {
		LOGGER.debug("Updating Sale Order status for Order ID : {}",orderId);
		LOGGER.debug("Checking whether Order exists");
		Boolean orderExists = saleOrderDao.isExists(orderId);
		if(!orderExists){
			LOGGER.error("Sale Order doesn't exists. Please contact Administrator");
			throw new OrderException("Sale Order doesn't exists");
		}
		
		if(EnumUtils.isValidEnum(SaleOrderStatus.class, saleOrderStatus.getValue())){
			LOGGER.debug("Fetching sale order with id :{}",orderId);
			SaleOrder saleOrder = (SaleOrder) saleOrderDao.findById(orderId);
			LOGGER.debug("Setting status to sale order");
			saleOrder.setSaleOrderStatus(saleOrderStatus);
			saleOrderDao.save(saleOrder);
			LOGGER.debug("Sale order status updated successfully");
		}else{
			LOGGER.error("Invalid Sale Order Status. Opeartion Aborted..!");
			throw new OrderException("Invalid Sale order status.");
		}
	}

	@Override
	public String getNextJobNo() {
		Long sequenceId = sequenceDao.findNextSequenceId(Sequence.SALE_ORDER_SEQUENCE);
		return  "A"+sequenceId;
	}

	@Override
	public ReportResponse generateSaleReport(ReportRequest reportRequest) {
		
		SaleOrderReport saleOrderReport = new SaleOrderReport();
		saleOrderReport.setCriteria((SaleOrderSearchCriteria) reportRequest.getQueryCriteria());
		
		List<Map<String,Object>> datas = saleOrderDao.getSaleReport(saleOrderReport);
	
		/**
		 * Setting staff name to id field if group by is staff concerned
		 * TODO Some generic logic should be applied
		 */
		if(saleOrderReport.getCriteria().getGroupBy().equals("staffConcerned")){
			for (Map<String, Object> data : datas) {
				
				if(data.get("_id") != null){
					Staff staff = staffService.getById(data.get("_id").toString());
					if(staff != null){
						data.put("_id",staff.getFullName());
					}
				}
			}
		}
		
		return new ReportResponse(null, datas);
	}

	@Override
	public Long getSaleOrderCount() {
		return saleOrderDao.findSaleOrderCount();
	}

	@Override
	public SaleOrder getById(String id) {

		SaleOrder order = saleOrderDao.findById(id);
		/* 
		 * Setting Party to Object
		 * TODO
		 */
		Customer customer = customerDao.findById(order.getPartyId());
		order.setParty(customer);
		
		//Setting Staff Concerned details
		Staff staffConcerned = staffService.getById(order.getStaffConcerned());
		order.setStaffConcernedText(staffConcerned.getFullName());
		
		return order;
	}
}
