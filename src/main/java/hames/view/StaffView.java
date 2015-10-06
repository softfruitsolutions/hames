package hames.view;

import hames.bean.Staff;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.enums.StaffStatusEnum;
import hames.service.StaffRoleService;
import hames.service.StaffService;

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
public class StaffView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(StaffView.class);

	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffRoleService staffRoleService;
	
	public String getTitleDefinition(Model model){
		ModelUtil.addMessages(model);
		return "staff";
	}
	
	@RequestMapping("/staffview")
	public String view(Model model, @RequestParam(value="id",required=false) Long id){
		activeMenu(model, "staff");
		
		Staff staff = null;
		if(id == null || id == 0){
			if(!model.containsAttribute("staff")){
				staff = new Staff();
				staff.setStatus(StaffStatusEnum.ACTIVE_STAFF.getValue());
				model.addAttribute("staff", staff);
			}
		}else{
			staff = staffService.findOne(id);
			model.addAttribute("staff", staff);
		}
		
		model.addAttribute("staffRoles", staffRoleService.findAll());
		return getTitleDefinition(model);
	}
	
	@RequestMapping("/staffsave")
	public String save(Model model,@ModelAttribute Staff staff,BindingResult result){
		
		logger.debug("Saving Staff : {} ",staff.toString());
		staffService.validate(result, staff);
		if(result.hasErrors()){
			return view(model,staff.getStaffId());
		}
		
		staffService.save(staff);
		logger.debug("Staff saved successfully");
		ModelUtil.addSuccess("Staff saved successfully");
		ModelUtil.addMessages(model);
		
		return view(model,null);
	}
}
