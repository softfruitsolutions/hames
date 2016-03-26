package com.hames.party.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

import com.hames.util.model.QueryCriteria;

public class PartySearchCriteria extends Party implements QueryCriteria{

	@Override
	public Criteria queryCriteria() {
		List<Criteria> andCriterias = buildCriteria();

		if(andCriterias == null || andCriterias.size() <= 0){
			return null;
		}else{
			return new Criteria().andOperator(andCriterias.toArray(new Criteria[andCriterias.size()]));	
		}
	}

	@Override
	public List<Criteria> buildCriteria() {
		
		List<Criteria> andCriterias = new ArrayList<Criteria>();
		
		if(getPartyType() != null){
			andCriterias.add(Criteria.where("partyType").is(getPartyType()));
		}
		
		return andCriterias;
	}

}
