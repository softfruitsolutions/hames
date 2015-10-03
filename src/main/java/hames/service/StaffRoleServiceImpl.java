package hames.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import hames.bean.StaffRole;
import hames.core.service.AbstractServiceImpl;
import hames.validator.StaffRoleValidator;

@Service
public class StaffRoleServiceImpl extends AbstractServiceImpl implements StaffRoleService {

	@Override
	public Validator getValidator() {
		return new StaffRoleValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return StaffRole.class;
	}

}
