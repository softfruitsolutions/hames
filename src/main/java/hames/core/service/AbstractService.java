package hames.core.service;

import hames.core.dao.AbstractDao;

import java.util.List;

import org.springframework.validation.Validator;

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
	 * Find one
	 * @param id
	 * @return T
	 */
	public <T> T findOne(Long id);
	
	/**
	 * Find all of an entity class
	 * @return
	 */
	public <T> List<T> findAll();
	
	/**
	 * Validate a Validator
	 * @param result
	 */
	public abstract <T> void validate(T t);
	
	/**
	 * Get Validator
	 * @return
	 */
	public abstract Validator getValidator();

	/**
	 * Get Entity Class
	 * @return
	 */
	public Class<?> getEntityClass();
}
