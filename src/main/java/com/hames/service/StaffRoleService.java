package com.hames.service;

import java.util.List;

import com.hames.bean.StaffRole;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;


public interface StaffRoleService{

	/**
	 * Save StaffRole
	 * @param staffRole
	 */
	public void saveStaffRole(StaffRole staffRole);
	
	/**
	 * Get A StaffRole
	 * @param roleId
	 * @return
	 */
	public StaffRole getStaffRoleById(String roleId);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	/**
	 * Get All Staff Roles
	 * @return
	 */
	public List<StaffRole> getAllStaffRoles(); 
	
	/**
	 * Get Active Staff Roles
	 * @return
	 */
	public List<StaffRole> getActiveStaffRoles();
	
}
