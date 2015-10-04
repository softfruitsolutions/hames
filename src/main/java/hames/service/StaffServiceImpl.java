package hames.service;

import hames.bean.Staff;
import hames.bean.StaffRole;
import hames.core.service.AbstractServiceImpl;
import hames.enums.StaffStatusEnum;
import hames.validator.StaffValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Service
public class StaffServiceImpl extends AbstractServiceImpl implements StaffService {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
	
	@Autowired
	private StaffRoleService staffRoleService;
	
	@Override
	public Validator getValidator() {
		return new StaffValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return Staff.class;
	}
	
	@Override
	public <T> void save(T t) {
		Staff staff = (Staff) t;
		super.save(staff);
	}
	
	@Override
	public <T> void validate(BindingResult result, T t) {
		
		Staff staff = (Staff) t;
		if(staff.getRoleId() == null || staff.getRoleId() <= 0){
			logger.debug("Staff Role required");
			result.addError(new ObjectError("roleId", "Staff Role Required"));
		}
		
		StaffRole staffRole = staffRoleService.findOne(staff.getRoleId());
		if(staffRole == null){
			logger.debug("Staff Role doesn't exists");
			result.addError(new ObjectError("roleId", "Invalid Staff Role"));
		}
		
		if(staff.getStatus() == null || StaffStatusEnum.findEnum(staff.getStatus()) == null){
			logger.debug("Staff status is null");
			result.addError(new ObjectError("status", "Staff status required"));
		}

		super.validate(result, t);
	}

}
