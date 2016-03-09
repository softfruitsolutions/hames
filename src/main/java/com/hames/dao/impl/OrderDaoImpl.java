package com.hames.dao.impl;

import com.hames.bean.Order;
import com.hames.dao.OrderDao;
import com.hames.mongo.GenericDaoImpl;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	public static final String COLLECTION_NAME = "order";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
