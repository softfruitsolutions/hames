package com.hames.view;

import org.apache.shiro.SecurityUtils;
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

import com.hames.bean.ExpenseCategory;
import com.hames.bean.ExpenseManager;
import com.hames.bean.Payment;
import com.hames.bean.PaymentItems;
import com.hames.enums.ExpenseStatus;
import com.hames.exception.ExpenseManagerException;
import com.hames.exception.ValidationException;
import com.hames.service.ExpenseManagerService;
import com.hames.system.auth.Permission;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping(value="/expense")
public class ExpenseManagerView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(ExpenseManagerView.class);

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
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveExpense(Model model,@ModelAttribute ExpenseManager expenseManager){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_EXPENSE_MANAGER.getPermission())){
			return "error.403";
		}
		
		try{
			expenseManagerService.saveExpense(expenseManager);
			ModelUtil.addSuccess("Expense saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
		}catch (IllegalArgumentException e) {
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch(ExpenseManagerException e){
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		return viewExpense(model,null);
	}
	
	@RequestMapping(value="/datatable")
	public @ResponseBody DatatableResponse getDatatable(@ModelAttribute DatatableRequest request){
		return expenseManagerService.getDatatable(request);
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
	
	@RequestMapping(value="/category/save", method=RequestMethod.POST)
	public String saveExpenseCategory(Model model,@ModelAttribute ExpenseCategory expenseCategory){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_EXPENSE_CATEGORY.getPermission())){
			return "error.403";
		}
		
		try{
			expenseManagerService.saveExpenseCategory(expenseCategory);
			ModelUtil.addSuccess("Expense Category saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
		}catch (IllegalArgumentException e) {
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		return viewExpenseCategory(model,null);
	}
	
}
