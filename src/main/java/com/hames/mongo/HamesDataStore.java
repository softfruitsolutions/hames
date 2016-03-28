package com.hames.mongo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.mongodb.Mongo;


/**
 * Hames: Mongo specific operations
 *  
 * @author afilansari
 */
@Component
public class HamesDataStore extends MongoTemplate{
	
	private static final Logger logger = LoggerFactory.getLogger(HamesDataStore.class);

	
	public HamesDataStore(Mongo mongo, String databaseName) {
		super(mongo, databaseName);
	}
	
	@Autowired
	public HamesDataStore(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter){
		super(mongoDbFactory, mongoConverter);
	}
	

	public boolean exists(String id,String collectionName){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return super.exists(query,collectionName);
	}

	@SuppressWarnings("unchecked")
	public DatatableResponse getDatatablePagedResult(DatatableRequest request){
		
		logger.debug("Building Datatable");
		DatatableResponse datatableResponse = new DatatableResponse(request.getsEcho());
		
		logger.debug("Fetching total no of records in collection : {}",request.getMongoCollectionName());
		Long totalRecords = getCollection(request.getMongoCollectionName()).count();
		//datatableResponse.setiTotalRecords(totalRecords);
		

		logger.debug("Building Query");
		Query query = new Query();
		
		if(request.getCriteria() != null && request.getCriteria().queryCriteria() != null){
			query.addCriteria(request.getCriteria().queryCriteria());
		}
		
		query.limit(request.getiDisplayLength());
		query.skip(request.getiDisplayStart());
		
		if(request.getSortDirection() != null && request.getSortDirection().equals("desc")){
			query.with(new Sort(Sort.Direction.DESC,request.getSortField()));
		}else{
			query.with(new Sort(Sort.Direction.ASC,request.getSortField()));
		}
		
		logger.debug("Fetching records with query : {}",query.toString());
		List<?> results = find(query,request.getClazz(), request.getMongoCollectionName());
		datatableResponse.setiTotalDisplayRecords(new Long(results.size()));
		datatableResponse.setAaData(results);
		
		logger.debug("Datatable response builded successfully for request :{}",request.toString());
		return datatableResponse;
	}
}
