package com.hames.report;

import com.hames.util.model.QueryCriteria;

/**
 * Report Request
 * @author afilansari
 *
 */

public class ReportRequest {

	private QueryCriteria queryCriteria;

	public QueryCriteria getQueryCriteria() {
		return queryCriteria;
	}

	public void setQueryCriteria(QueryCriteria queryCriteria) {
		this.queryCriteria = queryCriteria;
	}
	
}
