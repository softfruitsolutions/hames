package hames.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hames.core.view.AbstractView;

@Controller
public class StaffRoleView extends AbstractView{

	@Override
	public String getTitleDefinition() {
		return "staff.role";
	}
	
	@RequestMapping("/staffroleview")
	public String view(Model model){
		model.addAttribute("menu", "staffrole");
		return getTitleDefinition();
	}

}
