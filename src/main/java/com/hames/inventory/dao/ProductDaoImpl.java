package com.hames.inventory.dao;

import org.springframework.stereotype.Repository;

import com.hames.inventory.model.Product;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

	private static final String COLLECTION_NAME = "product"; 
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
