package com.hames.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.SaleOrder;
import com.hames.dao.SaleOrderDao;
import com.hames.enums.OrderType;

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
	public SaleOrder findByOrderId(String orderId) {
		return (SaleOrder) hamesDataStore.findById(orderId, getEntityClass(),COLLECTION_NAME);
	}

}
