package hames.view;

import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderView extends AbstractView{

	@Override
	public String getTitleDefinition(Model model) {
		return "order";
	}
	
	
	@RequestMapping("/orderview")
	public String view(Model model){
		ModelUtil.removeMessages();
		model.addAttribute("menu", "order");
		return getTitleDefinition(model);
	}

}
