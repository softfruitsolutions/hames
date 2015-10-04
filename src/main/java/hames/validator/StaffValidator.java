package hames.validator;

import hames.bean.Staff;
import hames.enums.StaffStatusEnum;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StaffValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Staff staff = (Staff) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "","First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "","Last Name Required");
		
	}

}
