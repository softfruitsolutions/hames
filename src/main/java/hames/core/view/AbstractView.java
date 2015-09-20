package hames.core.view;

import org.springframework.ui.Model;

public abstract class AbstractView {

	public abstract String getTitleDefinition();

	public String view(Model model){
		return getTitleDefinition();
	}
}
