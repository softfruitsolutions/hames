package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.bean.SaleOrder;
import com.hames.enums.OrderType;
import com.hames.enums.SaleOrderStatus;
import com.hames.exception.OrderException;
import com.hames.exception.PaymentException;
import com.hames.order.model.SaleOrderCriteria;
import com.hames.service.CustomerService;
import com.hames.service.SaleOrderService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.ErrorCode;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.ErrorNode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

@Controller
@RequestMapping("/saleorder")
public class SaleOrderController extends GenericView{
	
	private static final Logger logger = LoggerFactory.getLogger(SaleOrderController.class);

	@Autowired
	private SaleOrderService saleOrderService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("")
	public String list(Model model){
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_SALE_ORDER.getPermission())){
			return "error.403";
		}
		
		model.addAttribute("saleOrderCriteria", new SaleOrderCriteria());
		model.addAttribute("menu", "viewsaleorder");
		return "sale.order.list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_SALE_ORDER.getPermission())){
			return "error.403";
		}
		
		model.addAttribute("menu", "createsaleorder");
		
		SaleOrder saleOrder = null;
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("saleOrder")){
				
				saleOrder = new SaleOrder();
				saleOrder.setJobNo(saleOrderService.getNextJobNo());
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
			
			PaymentItems paymentItem = new PaymentItems();
			paymentItem.setPaymentDate(new DateTime());
			saleOrder.getPayment().addPaymentItems(paymentItem);
			
			model.addAttribute("saleOrder", saleOrder);
			return "sale.order.service";
		}
		
		model.addAttribute("customers", customerService.getAllCustomers());
		return "sale.order";
	}

	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(Model model,@ModelAttribute SaleOrder order){
		JsonResponse response;
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_SALE_ORDER.getPermission())){
			throw new AuthorizationException();
		}
		
		saleOrderService.saveOrder(order);
		response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Order saved successfully"));
		
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(OrderException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleStaffException(Exception e){
		logger.debug("Order Exception : {}",e);
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}

	@ResponseBody
	@ExceptionHandler(PaymentException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleRoleException(Exception e){
		logger.debug("Payment Exception : {}",e);
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return saleOrderService.getDatatable(datatableRequest);
	}
	
}
