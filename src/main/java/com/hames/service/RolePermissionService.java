package com.hames.service;

import java.util.List;

import com.hames.bean.RolePermission;


public interface RolePermissionService extends GenericService<RolePermission>{
	
	/**
	 * Get Active Role Permissions
	 * @return
	 */
	public List<RolePermission> getActiveRolePermissions();
}
