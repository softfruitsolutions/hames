package com.hames.util.model;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

public interface QueryCriteria {
	
	/**
	 * Get Criteria
	 * @return
	 */
	public Criteria queryCriteria();

	/**
	 * Building Criteria (and,or,in) and returns List<Criteria>
	 * @return List<Crteria>
	 */
	public List<Criteria> buildCriteria();
}
