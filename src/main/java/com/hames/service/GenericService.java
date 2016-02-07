package com.hames.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.hames.exception.ValidationException;
import com.hames.util.peer.ModelUtil;

public abstract class GenericService{
	
	private static final Logger logger = LoggerFactory.getLogger(GenericService.class);

	/**
	 * Get Validator
	 * @return
	 */
	public abstract Validator getValidator();
	
	/**
	 * Get Entity Class
	 * @return
	 */
	public abstract Class<?> getEntityClass();
	
	
	public <T> void validate(T t){
		logger.debug("Validating {} class",t.getClass());
		Map<String, String> errorMap = new HashMap<String, String>();
		MapBindingResult errors = new MapBindingResult(errorMap, getEntityClass().getName());
		
		if(getValidator() != null){
			getValidator().validate(t, errors);
			if(errors.hasErrors()){
				StringBuilder errorMessage = new StringBuilder();
				for(ObjectError oe : errors.getAllErrors()){
					logger.debug("Error : {} ",oe.getDefaultMessage());
					errorMessage.append(oe.getDefaultMessage()).append(",");
				}
				errorMessage.delete(errorMessage.length()-1, errorMessage.length());
				
				throw new ValidationException(errorMessage.toString());
			}
		}
	}
	
	public <T> void validate(T t,Validator validator,Class<T> clazz){
		logger.debug("Validating {} class",t.getClass());
		Map<String, String> errorMap = new HashMap<String, String>();
		MapBindingResult errors = new MapBindingResult(errorMap, clazz.getName());
		
		if(getValidator() != null){
			validator.validate(t, errors);
			if(errors.hasErrors()){
				for(ObjectError oe : errors.getAllErrors()){
					logger.debug("Error : {} ",oe.getDefaultMessage());
					ModelUtil.addError(oe.getDefaultMessage());
				}
				
				throw new ValidationException();
			}
		}
	}
	
}
