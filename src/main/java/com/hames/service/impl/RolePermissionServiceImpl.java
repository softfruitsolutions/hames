package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.RolePermission;
import com.hames.dao.RolePermissionDao;
import com.hames.service.GenericServiceImpl;
import com.hames.service.RolePermissionService;
import com.hames.validator.RolePermissionValidator;

@Service
public class RolePermissionServiceImpl extends GenericServiceImpl<RolePermission> implements RolePermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public Validator getValidator() {
		return new RolePermissionValidator();
	}

	@Override
	public String save(RolePermission rolePermission) {
		
		//Setting Auditable Details
		rolePermission.setAuditableDetails(rolePermission.getRoleId());
		return super.save(rolePermission);
	}

	@Override
	public List<RolePermission> getActiveRolePermissions() {
		return rolePermissionDao.findActiveRolePermissions();
	}


}
