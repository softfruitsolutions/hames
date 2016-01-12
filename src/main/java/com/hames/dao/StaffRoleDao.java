package com.hames.dao;

import java.util.List;

import com.hames.bean.StaffRole;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;

public interface StaffRoleDao{

	/**
	 * Save Staff Role
	 * @param staffRole
	 */
	void save(StaffRole staffRole);
	
	/**
	 * Find a Staff Role
	 * @param roleId
	 * @return
	 */
	StaffRole findByStaffRoleId(String roleId);
	
	/**
	 * Find All Staff Roles
	 * @return
	 */
	List<StaffRole> findAllStaffRoles();
	
	/**
	 * Get Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);
	
	/**
	 * Find Active Staff Roles
	 * @return
	 */
	List<StaffRole> findActiveStaffRoles();
}
