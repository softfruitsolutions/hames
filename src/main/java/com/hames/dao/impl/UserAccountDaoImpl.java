package com.hames.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.dao.UserAccountDao;
import com.hames.mongo.GenericDaoImpl;
import com.hames.system.auth.UserAccount;

@Repository
public class UserAccountDaoImpl extends GenericDaoImpl<UserAccount> implements UserAccountDao {

	private static final String COLLECTION_NAME = "user_account";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
	@Override
	public boolean isUsernameExists(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return hamesDataStore.exists(query, getCollectionName());
	}

	@Override
	public boolean checkUAExistsForStaff(String staffId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("staffId").is(staffId));
		return hamesDataStore.exists(query, getCollectionName());
	}

}
