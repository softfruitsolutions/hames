package com.hames.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import com.hames.bean.UserContext;
import com.hames.exception.GenericServiceException;
import com.hames.exception.ValidationException;
import com.hames.util.ModelUtil;

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
				for(ObjectError oe : errors.getAllErrors()){
					logger.debug("Error : {} ",oe.getDefaultMessage());
					ModelUtil.addError(oe.getDefaultMessage());
				}
				
				throw new ValidationException();
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
