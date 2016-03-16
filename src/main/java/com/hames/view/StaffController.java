package com.hames.view;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hames.bean.Staff;
import com.hames.enums.StaffStatus;
import com.hames.exception.RolePermissionException;
import com.hames.report.ReportEngine;
import com.hames.service.RolePermissionService;
import com.hames.service.StaffService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.ErrorCode;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.ErrorNode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/staff")
public class StaffController extends GenericView{

	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;
	@Autowired
	private RolePermissionService staffRoleService;
	@Autowired
	private ReportEngine reportEngine;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_STAFF.getPermission())){
			return "error.403";
		}
		
		return "staff.list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_STAFF.getPermission())){
			return "error.403";
		}
		
		Staff staff = null;
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("staff")){
				staff = new Staff();
				staff.setStatus(StaffStatus.ACTIVE_STAFF);
				model.addAttribute("staff", staff);
			}
		}else{
			staff = staffService.getById(id);
			model.addAttribute("staff", staff);
		}
		
		model.addAttribute("staffRoles", staffRoleService.getActiveRolePermissions());
		model.addAttribute("staffStatus", StaffStatus.values());
		
		return "staff.view";
	}
	
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute Staff staff){
		JsonResponse response;
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_STAFF.getPermission())){
			throw new AuthorizationException();
		}
		
		staffService.save(staff);
		response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Staff saved successfully"));
		
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(RolePermissionException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleRoleException(Exception e){
		logger.debug("Role Exception : {}",e.getMessage());
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}
	
	
	@RequestMapping("/report")
	public void downloadReport(Model model,HttpServletResponse response){
		
		logger.debug("Building Staff Report");
		List<Staff> staffs = staffService.getAll();
		JRDataSource dataSource = new JRBeanCollectionDataSource(staffs);
		reportEngine.renderReport(response,dataSource, "staff.jrxml", null);
		logger.debug("Staff Report rendered succesfully");
		
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return staffService.getDatatable(datatableRequest);
	}

}
