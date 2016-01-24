package com.hames.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.StaffRole;
import com.hames.dao.StaffRoleDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
import com.hames.enums.StaffRoleStatus;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

@Repository
public class StaffRoleDaoImpl extends GenericDao implements StaffRoleDao {

	private static final String COLLECTION_NAME = "staff_role";
	
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
		return StaffRole.class;
	}
	
	@Override
	public void save(StaffRole staffRole) {
		if(!hamesDataStore.exists(staffRole.getRoleId(),COLLECTION_NAME)){
			staffRole.setRoleId(UUID.randomUUID().toString());	
		}
		hamesDataStore.save(staffRole,COLLECTION_NAME);
	}
	
	@Override
	public StaffRole findByStaffRoleId(String roleId) {
		return (StaffRole) hamesDataStore.findById(roleId,getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public DatatableResponse buildDatatable(DatatableRequest request) {
		request.setClazz(getEntityClass());
		request.setMongoCollectionName(COLLECTION_NAME);
		return hamesDataStore.getDatatablePagedResult(request);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<StaffRole> findAllStaffRoles() {
		return (List<StaffRole>) hamesDataStore.findAll(getEntityClass(),COLLECTION_NAME);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StaffRole> findActiveStaffRoles() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(StaffRoleStatus.ACTIVE_STAFFROLE));
		return (List<StaffRole>) hamesDataStore.find(query, getEntityClass(),COLLECTION_NAME);
	}

	
	
}