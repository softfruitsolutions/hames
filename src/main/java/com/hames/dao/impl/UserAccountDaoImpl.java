package com.hames.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.helper.UUIDHelper;
import com.hames.dao.UserAccountDao;
import com.hames.mongo.GenericDao;
import com.hames.mongo.HamesDataStore;
import com.hames.system.auth.UserAccount;

@Repository
public class UserAccountDaoImpl extends GenericDao implements UserAccountDao {

	private static final String COLLECTION_NAME = "user_account";

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
		return UserAccount.class;
	}

	@Override
	public void save(UserAccount userAccount) {
		if(!hamesDataStore.exists(userAccount.getAccountId(),COLLECTION_NAME)){
			userAccount.setAccountId(UUIDHelper.getUUID());
		}
		hamesDataStore.save(userAccount,COLLECTION_NAME);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccount> getUserAccounts() {
		return (List<UserAccount>) hamesDataStore.findAll(getEntityClass(), COLLECTION_NAME);
	}

	@Override
	public boolean isUsernameExists(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return hamesDataStore.exists(query, COLLECTION_NAME);
	}

	@Override
	public boolean checkUserAccountExistsForStaff(String staffId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("staffId").is(staffId));
		return hamesDataStore.exists(query, COLLECTION_NAME);
	}

}
