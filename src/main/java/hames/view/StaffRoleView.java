package hames.view;

import hames.bean.StaffRole;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.service.StaffRoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffRoleView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(StaffRoleView.class);

	@Autowired
	private StaffRoleService staffRoleService;
	
	@Override
	public String getTitleDefinition(Model model) {
		ModelUtil.addMessages(model);
		return "staff.role";
	}
	
	@RequestMapping("/staffroleview")
	public String view(Model model, @RequestParam(value="id",required=false) Integer id){
		
		activeMenu(model, "staffrole");
		
		if(id == null || id == 0){
			model.addAttribute("staffRole", new StaffRole());
		}
		
		return getTitleDefinition(model);
	}

	@RequestMapping("/staffrolesave")
	public String save(Model model,@ModelAttribute StaffRole staffRole,BindingResult result){
		
		ModelUtil.removeMessages();
		logger.debug("Saving Staff Role : {} ",staffRole.toString());
		staffRoleService.validate(result, staffRole);
		if(result.hasErrors()){
			return getTitleDefinition(model);
		}
		
		staffRoleService.save(staffRole);
		logger.debug("Staff Role saved successfully");
		ModelUtil.addSuccess("Staff Role saved successfully");
		ModelUtil.addMessages(model);
		
		return view(model,null);
	}
}
