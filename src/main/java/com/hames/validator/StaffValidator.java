package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.Staff;

public class StaffValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Staff staff = (Staff) obj;
		if(staff.getFirstName() == null || staff.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "", "First Name Required");
		}
		
		if(staff.getLastName() == null || staff.getLastName().isEmpty()){
			errors.rejectValue("lastName", "", "Last Name Required");
		}
		
	}

}
