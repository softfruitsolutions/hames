package com.hames.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.SaleOrder;
import com.hames.dao.SaleOrderDao;
import com.hames.enums.OrderType;
import com.hames.enums.SaleOrderStatus;
import com.hames.mongo.MongoOperators;
import com.hames.order.model.SaleOrderSearchCriteria;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Repository
public class SaleOrderDaoImpl extends OrderDaoImpl implements SaleOrderDao{

	@Override
	public Class<?> getEntityClass() {
		return SaleOrder.class;
	}
	
	@Override
	public boolean isJobNoExists(String jobNo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderType").is(OrderType.SALE_ORDER).and("jobNo").is(jobNo));
		return hamesDataStore.exists(query, COLLECTION_NAME);
	}

	@Override
	@SuppressWarnings("unchecked")
	public SaleOrder findByOrderId(String orderId) {
		return (SaleOrder) hamesDataStore.findById(orderId, getEntityClass(),COLLECTION_NAME);
	}

	/*db.order.aggregate(
		    [
		        
		        {
		          $group : {
		              _id : "$orderDate",
		              orderDate : {$min : "$orderDate"},
		              orders : {$sum : 1},
		              amountPaid : {$sum : "$payment.totalAmount"},
		              discountAmount: {$sum : "$payment.discountAmount"}
		          }
		        },
		        { 
		          $sort: {orderDate: 1}
		        }
		    ]
		)
	*/
	
	@Override
	public AggregationOutput getSaleReport() {
		
		DBObject groupFields = new BasicDBObject();
		groupFields.put(MongoOperators.ID, "$orderDate");
		groupFields.put("ordersCount", new BasicDBObject(MongoOperators.SUM,1));
		groupFields.put("amountPaid", new BasicDBObject(MongoOperators.SUM, "$payment.amountPaid"));
		groupFields.put("discountAmount", new BasicDBObject(MongoOperators.SUM,"$payment.discountAmount"));
		groupFields.put("balanceDue", new BasicDBObject(MongoOperators.SUM,"$payment.balanceDue"));
		groupFields.put("totalAmount", new BasicDBObject(MongoOperators.SUM, "$payment.totalAmount"));
		DBObject group = new BasicDBObject(MongoOperators.GROUP, groupFields);
		
		DBObject sortFields = new BasicDBObject();
		sortFields.put(MongoOperators.ID,1);
		DBObject sort = new BasicDBObject(MongoOperators.SORT,sortFields);
		
		List<DBObject> pipeline = new ArrayList<DBObject>();
		pipeline.add(group);
		pipeline.add(sort);
		
		return hamesDataStore.getCollection(COLLECTION_NAME).aggregate(pipeline);
	}

	@Override
	public Long findSaleOrderCount() {
		return hamesDataStore.count(null, COLLECTION_NAME);
	}

}
