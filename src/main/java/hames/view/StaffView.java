package hames.view;

import hames.bean.Staff;
import hames.bean.exception.ValidationException;
import hames.core.bean.ModelUtil;
import hames.core.system.ReportEngine;
import hames.core.view.AbstractView;
import hames.enums.StaffStatusEnum;
import hames.service.StaffRoleService;
import hames.service.StaffService;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	@Autowired
	private ReportEngine reportEngine;
	
	public String getTitleDefinition(Model model){
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
		
		try{
			staffService.save(staff);
			ModelUtil.addSuccess("Staff saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors present");
		}
		
		return view(model,null);
	}
	
	@RequestMapping("/staffReport")
	public void downloadReport(Model model,HttpServletResponse response){
		
		logger.debug("Building Staff Report");
		List<Staff> staffs = staffService.findAll();
		JRDataSource dataSource = new JRBeanCollectionDataSource(staffs);
		byte[] report = reportEngine.renderReport(response,dataSource, "staff.jrxml", null);
		logger.debug("Staff Report rendered succesfully");
		
	}
}
