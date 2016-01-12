package com.hames.service;

import java.util.List;

import com.hames.bean.Staff;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

public interface StaffService{

	/**
	 * Save Staff
	 * @param staff
	 */
	public void saveStaff(Staff staff);
	
	/**
	 * Get A Staff
	 * @param roleId
	 * @return
	 */
	public Staff getStaffById(String staffId);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	/**
	 * Get All Staffs 
	 * @return
	 */
	public List<Staff> getAllStaffs(); 
}
