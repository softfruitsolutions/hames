package com.hames.mongo;

import java.util.List;

import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface GenericDao<T> {
	
	/**
	 * Save an object
	 * @param t
	 */
	public void save(T t);

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
