package com.hames.dao;

import java.util.List;

import com.hames.bean.RolePermission;
import com.hames.mongo.GenericDao;

public interface RolePermissionDao extends GenericDao<RolePermission>{

	/**
	 * Find Active Roles Permissions
	 * @return
	 */
	List<RolePermission> findActiveRolePermissions();
	
}
