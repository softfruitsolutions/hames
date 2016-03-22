package com.hames.dao.impl;

import com.hames.dao.OrderDao;
import com.hames.mongo.GenericDaoImpl;

public class OrderDaoImpl<T> extends GenericDaoImpl<T> implements OrderDao<T> {

	public static final String COLLECTION_NAME = "order";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
