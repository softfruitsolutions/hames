package com.hames.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao extends GenericDao {

	@Autowired private HamesDataStore hamesDataStore;
	
	private static final String COLLECTION_NAME = "sequence";
	
	private static Map<String,Long> sequenceMap;

	@Override
	public Class<?> getEntityClass() {
		return Sequence.class;
	}
	
	private Map<String,Long> getSequenceMap(){
		sequenceMap = new HashMap<String, Long>();
		sequenceMap.put("sale_order", 100L);
		return sequenceMap;
	}
	
	@PostConstruct
	private void createCollectionAndSequence(){
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
			loadSequenceMap();
		}else{
			loadSequenceMap();
		}
	}

	@SuppressWarnings("rawtypes")
	private void loadSequenceMap(){
	    Iterator it = getSequenceMap().entrySet().iterator();
	    while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			if(!hamesDataStore.exists(entry.getKey().toString(), COLLECTION_NAME)){
				Sequence sequence = new Sequence(entry.getKey().toString(), (Long) entry.getValue());
				createSequence(sequence);
			}
	    }
	}

	private void createSequence(Sequence sequence){
		hamesDataStore.insert(sequence, COLLECTION_NAME);
	}
	
	public Long findNextSequenceId(String key){
		
		//Building query
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(key));
		
		Sequence sequence = (Sequence) hamesDataStore.findOne(query, getEntityClass(), COLLECTION_NAME);
		
		return sequence.getSequence();
	}
	
	public Long updateSequence(String key){
		
		//Building query
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(key));
		
		//Increase Sequence Id by 1
		Update update = new Update();
		update.inc("sequence", 1);
		
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		
		Sequence sequence = hamesDataStore.findAndModify(query, update, options, Sequence.class);
		
		return sequence.getSequence();
	}

}

