package com.hames.dao;

import java.util.List;

import com.hames.bean.Staff;
import com.hames.mongo.GenericDao;

public interface StaffDao extends GenericDao<Staff> {
	
	/**
	 * Find All Active Staffs
	 * @return
	 */
	List<Staff> findAllActiveStaffs();
	
}
