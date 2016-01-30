package com.hames.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.mongodb.Mongo;


/**
 * Hames: Mongo specific operations
 *  
 * @author afilansari
 */

public class HamesDataStore extends MongoTemplate{
	
	private static final Logger logger = LoggerFactory.getLogger(HamesDataStore.class);

	
	public HamesDataStore(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}
	
	public HamesDataStore(Mongo mongo, String databaseName,UserCredentials userCredentials) {
		super(mongo, databaseName, userCredentials);
	}

	public boolean exists(String id,String collectionName){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return super.exists(query,collectionName);
	}
	
	public DatatableResponse getDatatablePagedResult(DatatableRequest request){
		
		logger.debug("Building Datatable");
		DatatableResponse datatableResponse = new DatatableResponse(request.getsEcho());
		
		logger.debug("Fetching total no of records in collection : {}",request.getMongoCollectionName());
		Long totalRecords = getCollection(request.getMongoCollectionName()).count();
		datatableResponse.setiTotalRecords(totalRecords);

		Query query = new Query();
		
		logger.debug("Fetching all the records with query : {}",query.toString());
		List results = findAll(request.getClazz(), request.getMongoCollectionName());
		datatableResponse.setAaData(results);
		
		logger.debug("Datatable response builded successfully for request :{}",request.toString());
		return datatableResponse;
	}
}
