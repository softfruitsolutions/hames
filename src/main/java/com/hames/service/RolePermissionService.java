package com.hames.service;

import java.util.List;

import com.hames.bean.RolePermission;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;


public interface RolePermissionService{

	/**
	 * Save Role Permission
	 * @param rolePermission
	 */
	public void saveRolePermission(RolePermission rolePermission);
	
	/**
	 * Get A Role
	 * @param roleId
	 * @return
	 */
	public RolePermission getRoleById(String roleId);
	
	/**
	 * Get DataTable 
	 * @param request
	 * @return
	 */
	public DatatableResponse getDatatable(DatatableRequest request);
	
	/**
	 * Get All Role Permissions
	 * @return
	 */
	public List<RolePermission> getAllRolePermissions(); 
	
	/**
	 * Get Active Role Permissions
	 * @return
	 */
	public List<RolePermission> getActiveRolePermissions();
	
	/**
	 * Is Role Permission Exists
	 * @param roleId
	 * @return
	 */
	public boolean isRolePermissionExists(String roleId);
}
