package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.RolePermission;
import com.hames.dao.RolePermissionDao;
import com.hames.exception.ValidationException;
import com.hames.service.GenericService;
import com.hames.service.RolePermissionService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.validator.RolePermissionValidator;

@Service
public class RolePermissionServiceImpl extends GenericService implements RolePermissionService {

	private static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public Validator getValidator() {
		return new RolePermissionValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return RolePermission.class;
	}
	
	@Override
	public void saveRolePermission(RolePermission rolePermission) {
		/**
		 * Validating Role
		 */
		try{
			validate(rolePermission);
		}catch(ValidationException e){
			throw new ValidationException();
		}
		
		//Setting Auditable Details
		rolePermission.setAuditableDetails(rolePermission.getRoleId());
		
		logger.debug("Saving entity : {},{}",rolePermission.getClass(),rolePermission.toString());
		rolePermissionDao.save(rolePermission);
		logger.debug("Entity saved successfully");
	}

	@Override
	public RolePermission getRoleById(String roleId) {
		return rolePermissionDao.findByRoleId(roleId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return rolePermissionDao.buildDatatable(request);
	}

	@Override
	public List<RolePermission> getAllRolePermissions() {
		return rolePermissionDao.findAllRolePermissions();
	}

	@Override
	public List<RolePermission> getActiveRolePermissions() {
		return rolePermissionDao.findActiveRolePermissions();
	}

	@Override
	public boolean isRolePermissionExists(String roleId) {
		return rolePermissionDao.isRolePermissionExists(roleId);
	}

}
