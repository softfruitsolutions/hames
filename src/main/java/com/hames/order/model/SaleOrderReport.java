package com.hames.order.model;

import java.util.ArrayList;
import java.util.List;

import com.hames.mongo.HamesDBObject;
import com.hames.mongo.MongoOperators;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Sale Order Report
 * @author afilansari
 *
 */
public class SaleOrderReport {
	
	private SaleOrderSearchCriteria criteria;

	public List<DBObject> getPipeline(){
		
		List<DBObject> pipeline = new ArrayList<DBObject>();
		
		//Match
		HamesDBObject match = new HamesDBObject(MongoOperators.MATCH);
		match.setValue(criteria.queryCriteria().getCriteriaObject());
		
		//Group
		HamesDBObject group = new HamesDBObject(MongoOperators.GROUP);
		group.put(MongoOperators.ID, "$"+criteria.getGroupBy());
		group.put("ordersCount", new BasicDBObject(MongoOperators.SUM,1));
		group.put("amountPaid", new BasicDBObject(MongoOperators.SUM, "$payment.amountPaid"));
		group.put("discountAmount", new BasicDBObject(MongoOperators.SUM,"$payment.discountAmount"));
		group.put("balanceDue", new BasicDBObject(MongoOperators.SUM,"$payment.balanceDue"));
		group.put("totalAmount", new BasicDBObject(MongoOperators.SUM, "$payment.totalAmount"));
		
		//Sort
		HamesDBObject sort = new HamesDBObject(MongoOperators.SORT);
		sort.put(MongoOperators.ID,1);
		
		pipeline.add(match.get());
		pipeline.add(group.get());
		pipeline.add(sort.get());
		
		return pipeline;
	}
	
	public SaleOrderSearchCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(SaleOrderSearchCriteria criteria) {
		this.criteria = criteria;
	}
	
}
