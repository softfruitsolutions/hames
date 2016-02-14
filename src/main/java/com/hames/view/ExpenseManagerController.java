package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
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

import com.hames.bean.ExpenseCategory;
import com.hames.bean.ExpenseManager;
import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.enums.ExpenseStatus;
import com.hames.exception.ExpenseManagerException;
import com.hames.service.ExpenseManagerService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.ErrorCode;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.ErrorNode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

@Controller
@RequestMapping(value="/expense")
public class ExpenseManagerController extends GenericView{

	private static final Logger logger = LoggerFactory.getLogger(ExpenseManagerController.class);

	@Autowired private ExpenseManagerService expenseManagerService;
	
	public void initExpense(Model model){
		model.addAttribute("expenseCategories", expenseManagerService.getAllExpenseCategory());
	}
	
	@RequestMapping(value="/list")
	public String viewExpenseList(Model model){
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_EXPENSE_MANAGER.getPermission())){
			return "error.403";
		}
		
		return "expense.manager.list";
	}
	
	/**
	 * View Expense Manager
	 * @param model
	 * @param expenseId
	 * @return
	 */
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String viewExpense(Model model,@RequestParam(value="id",required=false) String expenseId){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_EXPENSE_MANAGER.getPermission())){
			return "error.403";
		}
		
		if(expenseId == null || expenseId.isEmpty()){
			if(!model.containsAttribute("expenseManager")){
				ExpenseManager expenseManager = new ExpenseManager();

				Payment payment = new Payment();
				payment.addPaymentItems(new PaymentItems());
				
				expenseManager.setPayment(payment);
				expenseManager.setStatus(ExpenseStatus.DRAFT);
				
				model.addAttribute("expenseManager", expenseManager);
			}
		}else{
			ExpenseManager expenseManager = expenseManagerService.getExpense(expenseId);
			model.addAttribute("expenseManager", expenseManager);
		}
		
		initExpense(model);
		
		return "expense.manager.view";
	}

	@RequestMapping(value="/addPay")
	public String addPaymentItems(Model model,@ModelAttribute ExpenseManager expenseManager){
		Payment payment = expenseManager.getPayment();
		payment.addPaymentItems(new PaymentItems());
		expenseManager.setPayment(payment);
		
		initExpense(model);
		
		return "expense.manager.view";
	}
	
	/**
	 * Save Expense Manager
	 * @param model
	 * @param expenseManager
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public JsonResponse saveExpense(Model model,@ModelAttribute ExpenseManager expenseManager){
		
		JsonResponse response;
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_EXPENSE_MANAGER.getPermission())){
			throw new AuthorizationException();
		}
		
		expenseManagerService.saveExpense(expenseManager);
		response = new JsonResponse(Boolean.TRUE, new SuccessNode(SuccessCode.ENTITY_SAVED, "Expense saved successfully"));
		
		return response;
	}
	
	@RequestMapping(value="/datatable")
	public @ResponseBody DatatableResponse getDatatable(@ModelAttribute DatatableRequest request){
		return expenseManagerService.getDatatable(request);
	}
	
	@ResponseBody
	@ExceptionHandler(ExpenseManagerException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleExpenseManagerException(Exception e){
		logger.debug("Expense Manager exception. {}",e);
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR, HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}
	
	/**
	 * Expense Category
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public String viewExpenseCategory(Model model,@RequestParam(value="id",required=false) String categoryId){
	
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_EXPENSE_CATEGORY.getPermission())){
			return "error.403";
		}
		
		ExpenseCategory expenseCategory = null;
		
		if(categoryId == null || categoryId.isEmpty()){
			expenseCategory = new ExpenseCategory();
		}else{
			expenseCategory = expenseManagerService.getExpenseCategory(categoryId);
		}
		
		model.addAttribute("expenseCategory",expenseCategory);		
		model.addAttribute("expenseCategories", expenseManagerService.getAllExpenseCategory());
		
		return "expense.category.view";
	}
	
	/**
	 * Expense Category 
	 * @param model
	 * @param expenseCategory
	 * @return
	 */
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/category/save", method=RequestMethod.POST)
	public JsonResponse saveExpenseCategory(Model model,@ModelAttribute ExpenseCategory expenseCategory){
		
		JsonResponse response;
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_EXPENSE_CATEGORY.getPermission())){
			throw new AuthorizationException();
		}
		
		expenseManagerService.saveExpenseCategory(expenseCategory);
		response = new JsonResponse(Boolean.TRUE, new SuccessNode(SuccessCode.ENTITY_SAVED, "Expense category saved successfully"));

		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleIllegalArgumentException(Exception e){
		logger.debug("Illegal Argument exception. {}",e);
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR, HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}
}
