package hames.view;

import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomerView extends AbstractView {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerView.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public String getTitleDefinition(Model model) {
		return "customer";
	}
	
	@RequestMapping(value = "/customerview", method = RequestMethod.GET)
	public String view(Model model) {
		ModelUtil.removeMessages();
		model.addAttribute("menu", "customer");
		ModelUtil.addSuccess("Successfully Messages bundled loaded");
		ModelUtil.addMessages(model);
		return getTitleDefinition(model);
	}
	
}
