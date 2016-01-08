package hames.core.service;

import hames.core.dao.AbstractDao;
import hames.core.util.DatatableRequest;
import hames.core.util.DatatableResponse;

import java.util.List;

import org.springframework.validation.Validator;

public interface AbstractService extends AbstractDao{

	/**
	 * Validate and Save Entity
	 * @param t extends class
	 */
	public <T> void save(T t);
	
	/**
	 * Validate and Update Entity
	 * @param t extends class
	 */
	public <T> void validateAndUpdate(T t);
	
	/**
	 * Build JQuery Datatable
	 * 
	 * If Clazz parameter is null, then getEntityClass() value is taken from the 
	 * service layer.
	 *  
	 * @return
	 */
	public <T> DatatableResponse getDataTable(DatatableRequest datatableRequest,Class clazz);
	
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
