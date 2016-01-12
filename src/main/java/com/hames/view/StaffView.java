package com.hames.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.bean.Staff;
import com.hames.enums.StaffStatusEnum;
import com.hames.exception.StaffRoleException;
import com.hames.exception.ValidationException;
import com.hames.service.StaffRoleService;
import com.hames.service.StaffService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/staff")
public class StaffView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(StaffView.class);

	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffRoleService staffRoleService;
	//@Autowired
	//private ReportEngine reportEngine;
	
	@RequestMapping("/list")
	public String list(Model model){
		activeMenu(model, "staff");
		return "staff.list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		activeMenu(model, "staff");
		
		Staff staff = null;
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("staff")){
				staff = new Staff();
				staff.setStatus(StaffStatusEnum.ACTIVE_STAFF);
				model.addAttribute("staff", staff);
			}
		}else{
			staff = staffService.getStaffById(id);
			model.addAttribute("staff", staff);
		}
		
		model.addAttribute("staffRoles", staffRoleService.getActiveStaffRoles());
		model.addAttribute("staffStatus", StaffStatusEnum.values());
		
		return "staff.view";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Model model,@ModelAttribute Staff staff,BindingResult result){
		
		try{
			staffService.saveStaff(staff);
			ModelUtil.addSuccess("Staff saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors present");
			return view(model,null);
		}catch (StaffRoleException e) {
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
			return view(model,null);
		}
		
		return list(model);
	}
	
	/*@RequestMapping("/report")
	public void downloadReport(Model model,HttpServletResponse response){
		
		logger.debug("Building Staff Report");
		List<Staff> staffs = staffService.getAllStaffs();
		//JRDataSource dataSource = new JRBeanCollectionDataSource(staffs);
		//reportEngine.renderReport(response,dataSource, "staff.jrxml", null);
		logger.debug("Staff Report rendered succesfully");
		
	}*/
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return staffService.getDatatable(datatableRequest);
	}

}
