package com.hames.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.hames.exception.ValidationException;
import com.hames.mongo.GenericDao;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

@Service
public abstract class GenericServiceImpl<T> implements GenericService<T> {
	
	@Autowired
	private GenericDao<T> genericDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericServiceImpl.class);

	/**
	 * Specify validator by overriding this.
	 * @return
	 */
	public abstract Validator getValidator();
	
	@Override
	public String save(T t) {
		LOGGER.debug("Saving entity class : {} with value : {}",t.getClass(),t.toString());
		validate(t);
		return genericDao.save(t);
	}


	@Override
	public T getById(String id) {
		return genericDao.findById(id);
	}


	@Override
	public List<T> getAll() {
		return genericDao.findAll();
	}
	
	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return genericDao.getPagedDatatable(request);
	}
	
	@Override
	public Boolean isExists(String id) {
		return genericDao.isExists(id);
	}
	
	public void validate(T t){
		LOGGER.debug("Validating class:{}",t.getClass());
		Map<String, String> errorMap = new HashMap<String, String>();
		MapBindingResult errors = new MapBindingResult(errorMap, t.getClass().getName());
		
		if(getValidator() != null){
			getValidator().validate(t, errors);
			if(errors.hasErrors()){
				StringBuilder errorMessage = new StringBuilder();
				for(ObjectError oe : errors.getAllErrors()){
					LOGGER.debug("Error : {} ",oe.getDefaultMessage());
					errorMessage.append(oe.getDefaultMessage()).append(",");
				}
				errorMessage.delete(errorMessage.length()-1, errorMessage.length());
				
				throw new ValidationException(errorMessage.toString());
			}
		}
	}
	
	/*public void validate(T t,Validator validator,Class<T> clazz){
		LOGGER.debug("Validating {} class",t.getClass());
		Map<String, String> errorMap = new HashMap<String, String>();
		MapBindingResult errors = new MapBindingResult(errorMap, clazz.getName());
		
		if(getValidator() != null){
			validator.validate(t, errors);
			if(errors.hasErrors()){
				StringBuilder errorMessage = new StringBuilder();
				for(ObjectError oe : errors.getAllErrors()){
					LOGGER.debug("Error : {} ",oe.getDefaultMessage());
					errorMessage.append(oe.getDefaultMessage()).append(",");
				}
				errorMessage.delete(errorMessage.length()-1, errorMessage.length());
				
				throw new ValidationException(errorMessage.toString());
			}
		}
	}*/

}
