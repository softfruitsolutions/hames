package hames.validator;

import hames.bean.StaffRole;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StaffRoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		return StaffRole.class.equals(obj);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "","Role Name Required");
	}

}
