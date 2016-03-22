package com.hames.party.dao;

import org.springframework.stereotype.Repository;

import com.hames.mongo.GenericDaoImpl;
import com.hames.party.model.SupplierType;

@Repository
public class SupplierTypeDaoImpl extends GenericDaoImpl<SupplierType> implements SupplierTypeDao {

	private final String COLLECTION_NAME = "supplier_type";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
