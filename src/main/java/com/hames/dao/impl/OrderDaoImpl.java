package com.hames.dao.impl;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.hames.bean.Order;
import com.hames.dao.OrderDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public class OrderDaoImpl extends GenericDao implements OrderDao {

	public static final String COLLECTION_NAME = "order";
	
	@Autowired
	public HamesDataStore hamesDataStore;

	@PostConstruct
	public void createCollection() {
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
		}
	}

	@Override
	public Class<?> getEntityClass() {
		return Order.class;
	}

	@Override
	public void save(Order order) {
		if(!hamesDataStore.exists(order.getOrderId(),COLLECTION_NAME)){
			order.setOrderId(UUID.randomUUID().toString());
		}
		hamesDataStore.save(order,COLLECTION_NAME);
	}

	@Override
	public DatatableResponse buildDatatable(DatatableRequest request) {
		request.setClazz(getEntityClass());
		request.setMongoCollectionName(COLLECTION_NAME);
		
		return hamesDataStore.getDatatablePagedResult(request);
	}

	@Override
	public boolean orderExists(String orderId) {
		return hamesDataStore.exists(orderId, COLLECTION_NAME);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByOrderId(String orderId) {
		return (T) hamesDataStore.findById(orderId, getEntityClass());
	}

}
