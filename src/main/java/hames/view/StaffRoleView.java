package hames.view;

import hames.bean.StaffRole;
import hames.bean.exception.ValidationException;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.enums.StaffRoleStatusEnum;
import hames.service.StaffRoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "staff.role";
	}
	
	@RequestMapping("/staffroleview")
	public String view(Model model){
		model.addAttribute("staffRoles", staffRoleService.findAll());
		return "staff.role.list";
	}
	
	@RequestMapping("/staffrole")
	public String create(Model model, @RequestParam(value="id",required=false) Long id){
		
		activeMenu(model, "staffrole");
		
		StaffRole staffRole = null;
		
		if(id == null || id == 0){
			staffRole = new StaffRole();
			staffRole.setStatus(StaffRoleStatusEnum.ACTIVE_STAFFROLE.getValue());
		}else{
			staffRole = staffRoleService.findOne(id);
		}
		
		model.addAttribute("staffRole", staffRole);
		return getTitleDefinition(model);
	}

	@RequestMapping("/staffrolesave")
	public String save(Model model,@ModelAttribute StaffRole staffRole){
		
		try{
			staffRoleService.validateAndSave(staffRole);
			ModelUtil.addSuccess("Staff Role saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
		}
		
		return "redirect:staffroleview";
	}
}
