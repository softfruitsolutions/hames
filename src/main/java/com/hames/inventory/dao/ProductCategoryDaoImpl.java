package com.hames.inventory.dao;

import org.springframework.stereotype.Repository;

import com.hames.inventory.model.ProductCategory;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class ProductCategoryDaoImpl extends GenericDaoImpl<ProductCategory> implements ProductCategoryDao{

	private static final String COLLECTION_NAME = "product_category";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
