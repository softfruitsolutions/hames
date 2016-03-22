package com.hames.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.Staff;
import com.hames.dao.StaffDao;
import com.hames.enums.StaffStatus;
import com.hames.mongo.GenericDaoImpl;

@Repository
public class StaffDaoImpl extends GenericDaoImpl<Staff> implements StaffDao {

	private static final String COLLECTION_NAME = "staff";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public List<Staff> findAllActiveStaffs() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(StaffStatus.ACTIVE_STAFF));
		return (List<Staff>) findAllByQuery(query);
	}
}
