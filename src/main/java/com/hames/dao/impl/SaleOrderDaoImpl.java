package com.hames.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.SaleOrder;
import com.hames.dao.SaleOrderDao;
import com.hames.enums.OrderType;
import com.hames.order.model.SaleOrderReport;
import com.hames.util.peer.DateTimeUtil;
import com.mongodb.AggregationOutput;
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

	@Override
	public List<Map<String,Object>> getSaleReport(SaleOrderReport saleOrderReport) {
		
		System.out.println(saleOrderReport.getPipeline());
		
		AggregationOutput output = hamesDataStore.getCollection(COLLECTION_NAME).aggregate(saleOrderReport.getPipeline()); 
		
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		/**
		 * Mapping the aggregation output resultset
		 */
		for (DBObject resultSet : output.results()) {
			Map<String, Object> data = new HashMap<String, Object>();
			for (String	key : resultSet.keySet()) {
				
				//Converting to DateTime format result is an instaceof @java.util.Date
				if(resultSet.get(key) instanceof Date){
					data.put(key, new DateTime(resultSet.get(key)).toString(DateTimeUtil.getDefaultDateFormat()));
				}else{
					data.put(key, resultSet.get(key));
				}
				
			}
			datas.add(data);
		}
		
		return datas;
	}

	@Override
	public Long findSaleOrderCount() {
		return hamesDataStore.count(null, COLLECTION_NAME);
	}

}
