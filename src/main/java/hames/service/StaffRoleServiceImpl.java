package hames.service;

import hames.bean.StaffRole;
import hames.core.service.AbstractServiceImpl;
import hames.enums.StaffStatusEnum;
import hames.validator.StaffRoleValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Service
public class StaffRoleServiceImpl extends AbstractServiceImpl implements StaffRoleService {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Override
	public Validator getValidator() {
		return new StaffRoleValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return StaffRole.class;
	}
	
	@Override
	public <T> void validate(BindingResult result, T t) {
	
		StaffRole role = (StaffRole) t;
		if(role.getStatus() == null || StaffStatusEnum.findEnum(role.getStatus()) == null){
			logger.debug("Staff Role status is null");
			result.addError(new ObjectError("status", "Staff Role status required"));
		}
		super.validate(result, role);
	}

}
