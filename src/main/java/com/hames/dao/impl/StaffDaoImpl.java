package com.hames.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hames.bean.Staff;
import com.hames.dao.StaffDao;
import com.hames.db.GenericDao;
import com.hames.db.HamesDataStore;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

@Repository
public class StaffDaoImpl extends GenericDao implements StaffDao {

	private static final String COLLECTION_NAME = "staff";

	@Autowired
	private HamesDataStore hamesDataStore;
	
	@Override
	public Class<?> getEntityClass() {
		return Staff.class;
	}
	
	@PostConstruct
	public void createCollection() {
		if(!hamesDataStore.collectionExists(COLLECTION_NAME)){
			hamesDataStore.createCollection(COLLECTION_NAME);
		}
	}
	
	@Override
	public void save(Staff staff) {
		if(!hamesDataStore.exists(staff.getStaffId(), COLLECTION_NAME)){
			staff.setStaffId(UUID.randomUUID().toString());
		}
		hamesDataStore.save(staff);
	}

	@Override
	public Staff findByStaffId(String staffId) {
		return (Staff) hamesDataStore.findById(staffId, getEntityClass(),COLLECTION_NAME);
	}

	@Override
	public DatatableResponse buildDatatable(DatatableRequest request) {
		request.setClazz(getEntityClass());
		request.setMongoCollectionName(COLLECTION_NAME);
		
		return hamesDataStore.getDatatablePagedResult(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> findAllStaffs() {
		return (List<Staff>) hamesDataStore.findAll(getEntityClass());
	}

}
