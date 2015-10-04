package hames.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import hames.core.service.AbstractServiceImpl;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl implements CustomerService{

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
