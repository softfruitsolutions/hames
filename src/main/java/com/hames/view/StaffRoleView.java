package com.hames.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.bean.StaffRole;
import com.hames.enums.StaffRoleStatus;
import com.hames.exception.ValidationException;
import com.hames.service.StaffRoleService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/staffrole")
public class StaffRoleView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(StaffRoleView.class);

	@Autowired
	private StaffRoleService staffRoleService;
	
	@RequestMapping("/list")
	public String view(Model model){
		return "staff.role.list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		activeMenu(model, "staffrole");
		
		StaffRole staffRole = null;
		
		if(id == null || id.isEmpty()){
			staffRole = new StaffRole();
			staffRole.setStatus(StaffRoleStatus.ACTIVE_STAFFROLE);
		}else{
			staffRole = staffRoleService.getStaffRoleById(id);
			System.err.println(staffRole.getDateCreated());
		}
		
		model.addAttribute("staffRole", staffRole);
		model.addAttribute("staffRoleStatus", StaffRoleStatus.values());
		
		return "staff.role";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Model model,@ModelAttribute StaffRole staffRole){
		
		try{
			staffRoleService.saveStaffRole(staffRole);
			ModelUtil.addSuccess("Staff Role saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
			return view(model, staffRole.getRoleId());
		}
		
		return view(model);
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return staffRoleService.getDatatable(datatableRequest);
	}
	
}
