package com.hames.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hames.dao.UserAccountDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
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
		hamesDataStore.save(userAccount,COLLECTION_NAME);
	}

}
