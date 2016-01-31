package com.hames.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.Customer;
import com.hames.dao.CustomerDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
import com.hames.enums.PartyStatus;
import com.hames.enums.PartyType;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

@Repository
public class CustomerDaoImpl extends GenericDao implements CustomerDao{

	private static final String COLLECTION_NAME = "party";
	
	@Autowired
	private HamesDataStore hamesDataStore;
	
	@PostConstruct
	public void createCollection() {
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
		}
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Customer.class;
	}

	@Override
	public void save(Customer customer) {
		if(!hamesDataStore.exists(customer.getPartyId(),COLLECTION_NAME)){
			customer.setPartyId(UUID.randomUUID().toString());
		}
		hamesDataStore.save(customer,COLLECTION_NAME);
	}

	@Override
	public Customer findByCustomerId(String customerId) {
		return (Customer) hamesDataStore.findById(customerId,getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public DatatableResponse buildDatatable(DatatableRequest request) {
		request.setClazz(getEntityClass());
		request.setMongoCollectionName(COLLECTION_NAME);
		
		return hamesDataStore.getDatatablePagedResult(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAllCustomers() {
		return (List<Customer>) hamesDataStore.findAll(getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public Long findCustomerCount() {
		Query query = new Query();
		query.addCriteria(Criteria.where("partyType").is("CUSTOMER").and("status").is("ACTIVE_CUSTOMER"));
		return hamesDataStore.getCollection(COLLECTION_NAME).count(query.getQueryObject());
	}
	
}
