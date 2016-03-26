package com.hames.order.dao;

import org.springframework.stereotype.Repository;

import com.hames.mongo.GenericDaoImpl;
import com.hames.order.model.PurchaseOrder;

@Repository
public class PurchaseOrderDaoImpl extends GenericDaoImpl<PurchaseOrder> implements PurchaseOrderDao{

	private final String COLLECTION_NAME = "order";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
