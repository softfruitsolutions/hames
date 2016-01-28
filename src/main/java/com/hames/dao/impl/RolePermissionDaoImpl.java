package com.hames.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.RolePermission;
import com.hames.dao.RolePermissionDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
import com.hames.enums.RolePermissionStatus;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

@Repository
public class RolePermissionDaoImpl extends GenericDao implements RolePermissionDao {

	private static final String COLLECTION_NAME = "role_permission";
	
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
		return RolePermission.class;
	}
	
	@Override
	public void save(RolePermission rolePermission) {
		if(!hamesDataStore.exists(rolePermission.getRoleId(),COLLECTION_NAME)){
			rolePermission.setRoleId(UUID.randomUUID().toString());	
		}
		hamesDataStore.save(rolePermission,COLLECTION_NAME);
	}
	
	@Override
	public RolePermission findByRoleId(String roleId) {
		return (RolePermission) hamesDataStore.findById(roleId,getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public DatatableResponse buildDatatable(DatatableRequest request) {
		request.setClazz(getEntityClass());
		request.setMongoCollectionName(COLLECTION_NAME);
		return hamesDataStore.getDatatablePagedResult(request);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<RolePermission> findAllRolePermissions() {
		return (List<RolePermission>) hamesDataStore.findAll(getEntityClass(),COLLECTION_NAME);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolePermission> findActiveRolePermissions() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(RolePermissionStatus.ACTIVE_ROLE));
		return (List<RolePermission>) hamesDataStore.find(query, getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public boolean isRolePermissionExists(String roleId) {
		return hamesDataStore.exists(roleId, COLLECTION_NAME);
	}

	
	
}