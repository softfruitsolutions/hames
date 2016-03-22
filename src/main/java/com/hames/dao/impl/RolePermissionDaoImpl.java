package com.hames.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.RolePermission;
import com.hames.dao.RolePermissionDao;
import com.hames.enums.RolePermissionStatus;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class RolePermissionDaoImpl extends GenericDaoImpl<RolePermission> implements RolePermissionDao {

	private static final String COLLECTION_NAME = "role_permission";
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
	@Override
	public List<RolePermission> findActiveRolePermissions() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(RolePermissionStatus.ACTIVE_ROLE));
		return (List<RolePermission>) hamesDataStore.find(query, RolePermission.class, COLLECTION_NAME);
	}

}