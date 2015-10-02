package hames.core.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import hames.core.dao.AbstractDao;

public interface AbstractService extends AbstractDao{

	/**
	 * Save Entity
	 * @param t extends class
	 */
	public <T> void save(T t);
	
	/**
	 * Update Entity
	 * @param t extends class
	 */
	public <T> void update(T t);
	
	/**
	 * Validate a Validator
	 * @param result
	 */
	public abstract <T> void validate(BindingResult result,T t);
	
	/**
	 * Get Validator
	 * @return
	 */
	public abstract Validator getValidator();
	
}
