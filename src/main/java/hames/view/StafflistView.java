package hames.view;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hames.bean.Staff;
import hames.core.view.AbstractView;
import hames.dbo.StaffDbo;


@Controller
public class StafflistView extends AbstractView{

	@Override
	public String getTitleDefinition(Model model) {
		// TODO Auto-generated method stub
		return "stafflistview";
	}
	
	@RequestMapping("/stafflistview")
	
	public String view(Model model,@RequestParam(value="tag",required=false)String tag){
		activeMenu(model, "staff");
		ArrayList<Staff> list=new ArrayList<Staff>();
		StaffDbo getstaf=new StaffDbo();
		if(tag==null){
			tag="";
		}
		list=getstaf.getAll(tag);
		model.addAttribute("list", list);
		return "stafflistview";
	}
	
	
}
