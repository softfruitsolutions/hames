package hames.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import hames.bean.Customer;
import hames.core.service.AbstractServiceImpl;
import hames.enums.CustomerStatusEnum;
import hames.enums.StaffStatusEnum;
import hames.validator.CustomerValidator;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl implements CustomerService{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Validator getValidator() {
		return new CustomerValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return Customer.class;
	}

	@Override
	public <T> void validate(BindingResult result, T t) {
		Customer customer = (Customer) t;
		if(customer.getStatus() == null || CustomerStatusEnum.findEnum(customer.getStatus()) == null){
			logger.debug("Customer status is null");
			result.addError(new ObjectError("status", "Customer status required"));
		}

		super.validate(result, t);
	}

}
