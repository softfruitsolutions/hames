package hames.validator;

import hames.bean.Customer;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "","First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "","Last Name Required");

	}

}
