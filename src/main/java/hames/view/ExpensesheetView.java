package hames.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import hames.bean.Expense;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.dbo.ExpenseDbo;

@Controller
public class ExpensesheetView extends AbstractView{

	@Override
	public String getTitleDefinition(Model model) {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/expensesheet")
	public String ExpenseSheet(Model model){
		
		
		return "expensesheet";
	}
	@RequestMapping("/saveexpense")
	public String Expensesave(@ModelAttribute("SpringWeb")Expense expense,Model model){
		ExpenseDbo db=new ExpenseDbo();
		db.insertNewExpense(expense);
		ModelUtil.addSuccess("success..");
		return ExpenseSheet(model);
	}

}
