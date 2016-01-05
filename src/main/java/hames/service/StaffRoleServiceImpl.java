package hames.service;

import hames.bean.StaffRole;
import hames.core.bean.ModelUtil;
import hames.core.service.AbstractServiceImpl;
import hames.enums.StaffRoleStatusEnum;
import hames.validator.StaffRoleValidator;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
	public <T> void save(T t) {
		StaffRole role = (StaffRole) t;
		if(role.getRoleId() == null || role.getRoleId() < -1){
			role.setDateCreated(new DateTime());
			role.setDateModified(new DateTime());	
		}else{
			role.setDateModified(new DateTime());
		}
		
		super.save(t);
	}
	
	@Override
	public <T> void validate(T t) {
	
		StaffRole role = (StaffRole) t;
		if(role.getStatus() == null || StaffRoleStatusEnum.findEnum(role.getStatus()) == null){
			logger.debug("Staff Role status is null");
			ModelUtil.addError("Staff Role status is null");
		}
		super.validate(role);
	}

}
