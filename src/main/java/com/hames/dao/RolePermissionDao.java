package com.hames.dao;

import java.util.List;

import com.hames.bean.RolePermission;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

public interface RolePermissionDao{

	/**
	 * Save Role
	 * @param rolePermission
	 */
	void save(RolePermission rolePermissions);
	
	/**
	 * Find a Role
	 * @param roleId
	 * @return
	 */
	RolePermission findByRoleId(String roleId);
	
	/**
	 * Find All Roles
	 * @return
	 */
	List<RolePermission> findAllRolePermissions();
	
	/**
	 * Get Datatable
	 * @param request
	 * @return
	 */
	DatatableResponse buildDatatable(DatatableRequest request);
	
	/**
	 * Find Active Roles Permissions
	 * @return
	 */
	List<RolePermission> findActiveRolePermissions();
	
	/**
	 * Is role permission exists
	 * @param roleId
	 * @return
	 */
	boolean isRolePermissionExists(String roleId);
}
