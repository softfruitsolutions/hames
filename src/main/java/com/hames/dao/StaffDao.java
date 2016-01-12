package com.hames.dao;

import java.util.List;

import com.hames.bean.Staff;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

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
	
}
