package com.hames.service;

import java.util.List;

import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface GenericService<T> {
	
	/**
	 * Save an entity
	 * @return
	 */
	public String save(T t);
	
	/**
	 * Get by id
	 * @param id
	 * @return
	 */
	public T getById(String id);
	
	/**
	 * Get all
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * Is Exists
	 * @param id
	 * @return
	 */
	public Boolean isExists(String id);
	
	/**
	 * Get datatable from datatable request
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
 		
}
