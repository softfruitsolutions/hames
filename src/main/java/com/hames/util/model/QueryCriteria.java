package com.hames.util.model;

import org.springframework.data.mongodb.core.query.Criteria;

public interface QueryCriteria {
	
	/**
	 * Get Criteria
	 * @return
	 */
	public Criteria queryCriteria();

	/**
	 * Building Criteria
	 */
	public void buildCriteria();
}
