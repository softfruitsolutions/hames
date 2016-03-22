package com.hames.mongo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao extends GenericDaoImpl<Sequence>{

	private static final String COLLECTION_NAME = "sequence";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	private List<Sequence> getSequences(){
		List<Sequence> sequences = new ArrayList<Sequence>();
		sequences.add(new Sequence("sale_order","A",100L));
		sequences.add(new Sequence("product","P",100L));
		return sequences;
	}
	
	@PostConstruct
	private void sequenceChecker(){
		for (Sequence sequence : getSequences()) {
			if(!isExists(sequence.getSequenceName())){
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
		
		Sequence sequence = findByQuery(query);
		
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