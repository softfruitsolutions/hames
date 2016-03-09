package com.hames.mongo;

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
public class SequenceDao extends GenericDaoImpl<Sequence> {

	private static final String COLLECTION_NAME = "sequence";
	private static Map<String,Long> sequenceMap;

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	private Map<String,Long> getSequenceMap(){
		sequenceMap = new HashMap<String, Long>();
		sequenceMap.put("sale_order", 100L);
		return sequenceMap;
	}
	
	@PostConstruct
	private void sequenceChecker(){
		Iterator it = getSequenceMap().entrySet().iterator();
	    while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			if(!hamesDataStore.exists(entry.getKey().toString(), getCollectionName())){
				Sequence sequence = new Sequence(entry.getKey().toString(), (Long) entry.getValue());
				hamesDataStore.insert(sequence, COLLECTION_NAME);
			}
	    }
	}

	/**
	 * Finding next sequence id using key
	 * @param key
	 * @return
	 */
	public Long findNextSequenceId(String key){
		
		//Building query
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(key));
		
		Sequence sequence = (Sequence) hamesDataStore.findOne(query, Sequence.class, COLLECTION_NAME);
		
		return sequence.getSequence();
	}
	
	/**
	 * Updating sequence details
	 * @param key
	 * @return
	 */
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