package hames.view;

import hames.core.view.AbstractView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffView extends AbstractView{

	public String getTitleDefinition(Model model){
		return "staff";
	}
	
	@RequestMapping("/staffview")
	public String view(Model model){
		activeMenu(model, "staff");
		return getTitleDefinition(model);
	}
}
