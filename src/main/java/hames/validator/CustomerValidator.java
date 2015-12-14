package hames.validator;

import hames.bean.Customer;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer) obj;
		if(customer.getFirstName() == null || customer.getFirstName().isEmpty()){
			errors.rejectValue("firstName", "", "First Name Required");
		}
		
		if(customer.getLastName() == null || customer.getLastName().isEmpty()){
			errors.rejectValue("lastName", "", "Last Name Required");
		}

	}

}
