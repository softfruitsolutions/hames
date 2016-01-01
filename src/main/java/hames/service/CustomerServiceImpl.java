package hames.service;

import hames.bean.Customer;
import hames.core.bean.ModelUtil;
import hames.core.service.AbstractServiceImpl;
import hames.enums.CustomerStatusEnum;
import hames.validator.CustomerValidator;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

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
	public <T> void validate(T t) {
		Customer customer = (Customer) t;
		
		//Setting Auditable details
		customer.setDateCreated(new DateTime());
		customer.setDateModified(new DateTime());
		
		if(customer.getStatus() == null || CustomerStatusEnum.findEnum(customer.getStatus()) == null){
			logger.debug("Customer status is null");
			ModelUtil.addError("Customer Status Required");
		}
		super.validate(t);
	}

}
