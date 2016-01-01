package hames.service;

import hames.bean.Staff;
import hames.bean.StaffRole;
import hames.core.bean.ModelUtil;
import hames.core.service.AbstractServiceImpl;
import hames.enums.StaffStatusEnum;
import hames.validator.StaffValidator;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public <T> void validateAndSave(T t) {
		Staff staff = (Staff) t;
		
		//Setting Auditable details
		staff.setDateCreated(new DateTime());
		staff.setDateModified(new DateTime());
		
		super.validateAndSave(staff);
	}
	
	@Override
	public <T> void validate(T t) {
		
		Staff staff = (Staff) t;
		if(staff.getRoleId() == null || staff.getRoleId() <= 0){
			logger.debug("Staff Role required");
			ModelUtil.addError("Staff Role Required");
		}
		
		StaffRole staffRole = staffRoleService.findOne(staff.getRoleId());
		if(staffRole == null){
			logger.debug("Staff Role doesn't exists");
			ModelUtil.addError("Invalid Staff Role");
		}
		
		if(staff.getStatus() == null || StaffStatusEnum.findEnum(staff.getStatus()) == null){
			logger.debug("Staff status is null");
			ModelUtil.addError("Staff status required");
		}

		super.validate(t);
	}

}
