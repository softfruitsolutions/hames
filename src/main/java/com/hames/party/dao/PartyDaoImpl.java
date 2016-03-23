package com.hames.party.dao;

import com.hames.mongo.GenericDaoImpl;

public abstract class PartyDaoImpl<T> extends GenericDaoImpl<T> implements PartyDao<T>{

	private static final String COLLECTION_NAME = "party";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
}
