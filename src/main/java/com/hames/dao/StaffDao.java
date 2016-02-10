package com.hames.dao;

import java.util.List;

import com.hames.bean.Staff;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface StaffDao {

	/**
	 * Save Staff
	 * @param staff
	 */
	void save(Staff staff);
	
	/**
	 * Find a Staff
	 * @param staffId
	 * @return
	 */
	Staff findByStaffId(String staffId);
	
	/**
	 * Get Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);
	
	/**
	 * Find All Staffs
	 * @return
	 */
	List<Staff> findAllStaffs();
	
	/**
	 * Is Staff Exists
	 * @return
	 */
	boolean isStaffExists(String staffId);
	
	/**
	 * Find All Active Staffs
	 * @return
	 */
	List<Staff> findAllActiveStaffs();
	
}
