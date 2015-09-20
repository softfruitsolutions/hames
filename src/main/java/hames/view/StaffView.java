package hames.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffView{

	public String getTitleDefinition(){
		return "staff";
	}
	
	@RequestMapping("/staffview")
	public String view(Model model){
		model.addAttribute("menu", "staff");
		return getTitleDefinition();
	}
}
