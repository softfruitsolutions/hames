package com.hames.order.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

import com.hames.bean.SaleOrder;
import com.hames.util.model.QueryCriteria;

public class SaleOrderSearchCriteria extends SaleOrder implements QueryCriteria {
	
	@Override
	public Criteria queryCriteria() {
		List<Criteria> andCriterias = new ArrayList<Criteria>();
		
		if(getJobNo() != null && !getJobNo().isEmpty()){
			andCriterias.add(Criteria.where("jobNo").is(getJobNo()));
		}
		
		if(andCriterias == null || andCriterias.size() <= 0){
			return null;
		}else{
			return new Criteria().andOperator(andCriterias.toArray(new Criteria[andCriterias.size()]));	
		}
		
	}
	
	@Override
	public void buildCriteria(){
		
	}

}
