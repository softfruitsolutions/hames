package com.hames.inventory.dao;

import org.springframework.stereotype.Repository;

import com.hames.inventory.model.ProductGroup;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class ProductGroupDaoImpl extends GenericDaoImpl<ProductGroup> implements ProductGroupDao{

	private static final String COLLECTION_NAME = "product_group";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
