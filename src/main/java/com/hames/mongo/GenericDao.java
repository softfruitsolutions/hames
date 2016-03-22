package com.hames.mongo;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

/**
 * T Represent the generic type class to save
 * @author afilansari
 * @param <T> extends Object
 */
public interface GenericDao<T> {
	
	/**
	 * Save an object
	 * @param t
	 */
	public String save(T t);

	/**
	 * Find By Id
	 * @param id
	 * @return
	 */
	public T findById(String id);
	/**
	 * Find All
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Find By Query
	 * @param query
	 * @return T
	 */
	public T findByQuery(Query query);
	
	/**
	 * Find all by query
	 * @param query
	 * @return List<T>
	 */
	public List<T> findAllByQuery(Query query);

	/**
	 * Is 'id' exists in collection
	 * @param id
	 * @return
	 */
	public Boolean isExists(String id);
	/**
	 * Get Paged Datatable : jQueryDatatable
	 * @param request
	 * @return
	 */
	public DatatableResponse getPagedDatatable(DatatableRequest request);

}
