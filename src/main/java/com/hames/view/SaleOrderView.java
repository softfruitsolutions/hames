package com.hames.view;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.bean.SaleOrder;
import com.hames.enums.OrderType;
import com.hames.enums.SaleOrderStatus;
import com.hames.exception.OrderException;
import com.hames.exception.PaymentException;
import com.hames.exception.ValidationException;
import com.hames.service.CustomerService;
import com.hames.service.SaleOrderService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/saleorder")
public class SaleOrderView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(SaleOrderView.class);

	@Autowired
	private SaleOrderService saleOrderService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String view(Model model){
		model.addAttribute("menu", "viewsaleorder");
		return "sale.order.list";
	}
	
	@RequestMapping("/view")
	public String create(Model model, @RequestParam(value="id",required=false) String id){
		
		model.addAttribute("menu", "createsaleorder");
		
		SaleOrder saleOrder = null;
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("saleOrder")){
				
				saleOrder = new SaleOrder();
				saleOrder.setOrderDate(new DateTime());
				saleOrder.setSaleOrderStatus(SaleOrderStatus.DRAFT);
				saleOrder.setOrderType(OrderType.SALE_ORDER);
				
				/**
				 * Setting Payment to Sale Order
				 */
				Payment payment = new Payment();
				
				PaymentItems paymentItem = new PaymentItems();
				payment.addPaymentItems(paymentItem);
				saleOrder.setPayment(payment);
				
				model.addAttribute("saleOrder", saleOrder);
			}
		}else{
			saleOrder = saleOrderService.getOrderById(id);
			model.addAttribute("saleOrder", saleOrder);
			return "sale.order.service";
		}
		
		model.addAttribute("customers", customerService.getAllCustomers());
		return "sale.order";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Model model,@ModelAttribute SaleOrder order){
		
		try{
			saleOrderService.saveOrder(order);
			ModelUtil.addSuccess("Sale Order created successfully");	
		}catch (ValidationException e) {
			logger.error("Validation errors are present");
			return create(model,null);
		}catch (OrderException e) {
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
			return create(model,null);
		}catch (PaymentException e) {
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
			return create(model,null);
		}
		
		return view(model);
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return saleOrderService.getDatatable(datatableRequest);
	}
	
	@RequestMapping("/updatestatus")
	public String updateOrderStatus(Model model,@RequestParam(value="orderId")String orderId,
								  				@RequestParam(value="saleorderStatus")SaleOrderStatus saleOrderStatus,
								  				@RequestParam(value="jobNo")String jobNo){
		try{
			saleOrderService.updateOrderStatus(orderId, saleOrderStatus);
			logger.debug("Sale Order Status updated successfully");
			ModelUtil.addSuccess(jobNo+" status updated to "+saleOrderStatus);
		}catch(OrderException e){
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		return view(model);
	}
	
}
