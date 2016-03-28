package com.hames.inventory.dao;

import org.springframework.stereotype.Repository;

import com.hames.inventory.model.PriceList;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class PriceListDaoImpl extends GenericDaoImpl<PriceList> implements PriceListDao {

	private final static String COLLECTION_NAME = "pricelist";
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
