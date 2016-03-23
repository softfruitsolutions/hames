package com.hames.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.dao.CustomerDao;
import com.hames.mongo.GenericDaoImpl;
import com.hames.party.model.Customer;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao{

	private static final String COLLECTION_NAME = "party";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
	@Override
	public Long findCustomerCount() {
		Query query = new Query();
		query.addCriteria(Criteria.where("partyType").is("CUSTOMER").and("status").is("ACTIVE_CUSTOMER"));
		return hamesDataStore.getCollection(COLLECTION_NAME).count(query.getQueryObject());
	}

	@Override
	public List<Customer> findActiveCustomers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("partyType").is("CUSTOMER").and("status").is("ACTIVE_CUSTOMER"));
		return (List<Customer>) hamesDataStore.find(query, Customer.class, COLLECTION_NAME);
	}
	
}
