package hames.view;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hames.bean.Customer;
import hames.bean.Order;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.dbo.CustomerDbo;
import hames.dbo.OrderDbo;
import hames.service.ServicebillService;

@Controller
public class ServicebillView extends AbstractView{
	


	ServicebillService billservice;
	String date;
	@Override
	public String getTitleDefinition(Model model) {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/servicebill")
	public String servicebill(Model model,@RequestParam(value="id",required=false)String id){
		activeMenu(model, "servicebill");
		OrderDbo db=new OrderDbo();
		ArrayList<Order> orderlist=new ArrayList<Order>();
		orderlist=db.getAll(id);
		float sum=0.0f;
		for(int i=0;i<orderlist.size();i++){
			String crntvalue=""+orderlist.get(i).getBalanceDue();
			float val=Float.parseFloat(crntvalue);
			sum=sum+val;
		}
		String customername="";
		if(id!=null){
			CustomerDbo csdbo=new CustomerDbo();
			customername=csdbo.getCustomerName(""+id);
		}
		billservice=new ServicebillService();
		date=billservice.getToday();
		model.addAttribute("date", date);
		model.addAttribute("billitems", orderlist);
		model.addAttribute("sum", sum);
		model.addAttribute("customerid", id);
		model.addAttribute("confirm", "not");
		model.addAttribute("customername",customername);
		
		return "servicebill";
	}
	@RequestMapping("/searchbill")
	public String searchbill(Model model,@RequestParam(value="name",required=false)String name){

		CustomerDbo db=new CustomerDbo();
		ArrayList<Customer> list=new ArrayList<Customer>();
		list=db.getAll(name);
		model.addAttribute("customernames", list);
		return "servicebill";
	}
	@RequestMapping("/savebill")
	public String savebill(Model model,@RequestParam(value="order" ,required=false)String order,@RequestParam(value="payment",required=false)String payment
			,@RequestParam(value="disc",required=false)String discount){
		
		if(billservice.saveBill(order, payment)){
			ModelUtil.addSuccess("Payment Saved Succesfully");
			
		}
		else {
			ModelUtil.addError("Error on saving billl payement");
		}
		return servicebill(model, null);
		
	}
	@RequestMapping("/viewbillbyorder")
	public String viewbillbyorder(Model model,@RequestParam(value="id",required=false)String id){
		
		ArrayList<Order> list=new ArrayList<Order>();
		OrderDbo orderdb=new OrderDbo();
		list=orderdb.getById(id);
		float sum=0.0f;
		for(int i=0;i<list.size();i++){
			String crntvalue=""+list.get(i).getBalanceDue();
			float val=Float.parseFloat(crntvalue);
			sum=sum+val;
		}
		
		model.addAttribute("confrm", "ok");
		model.addAttribute("date", date);
		model.addAttribute("billitems", list);
		model.addAttribute("sum", sum);
		model.addAttribute("ordr", id);
		model.addAttribute("customername", new CustomerDbo().getCustomerName(""+list.get(0).getCustomerId()));
		return "servicebill";
	}
}
