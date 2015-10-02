package hames.core.view;

import org.springframework.ui.Model;

public abstract class AbstractView {

	public abstract String getTitleDefinition(Model model);
	
	public void activeMenu(Model model,String menuName){
		model.addAttribute("menu", menuName);
	}

	
}
