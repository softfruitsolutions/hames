package hames.validator;

import hames.bean.Staff;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StaffValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Staff customer = (Staff) obj;
		if(customer.getFirstName() == null || customer.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "", "First Name Required");
		}
		
		if(customer.getLastName() == null || customer.getLastName().isEmpty()){
			errors.rejectValue("lastName", "", "Last Name Required");
		}
		
	}

}
