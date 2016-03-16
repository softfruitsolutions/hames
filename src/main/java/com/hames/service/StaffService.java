package com.hames.service;

import java.util.List;

import com.hames.bean.Staff;

public interface StaffService extends GenericService<Staff>{
	
	/**
	 * Get All Active Staffs
	 * @return
	 */
	public List<Staff> getAllActiveStaffs();
}
