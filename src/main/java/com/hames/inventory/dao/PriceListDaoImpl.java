package com.hames.inventory.dao;

import org.springframework.stereotype.Repository;

import com.hames.inventory.model.PriceList;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class PriceListDaoImpl extends GenericDaoImpl<PriceList> implements PriceListDao {

	@Override
	public String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

}
