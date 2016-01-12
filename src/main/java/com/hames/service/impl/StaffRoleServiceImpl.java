package com.hames.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.StaffRole;
import com.hames.dao.StaffRoleDao;
import com.hames.exception.ValidationException;
import com.hames.service.GenericService;
import com.hames.service.StaffRoleService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.validator.StaffRoleValidator;

@Service
public class StaffRoleServiceImpl extends GenericService implements StaffRoleService {

	private static final Logger logger = LoggerFactory.getLogger(StaffRoleServiceImpl.class);

	@Autowired
	private StaffRoleDao staffRoleDao;

	@Override
	public Validator getValidator() {
		return new StaffRoleValidator();
	}

	@Override
	public Class<?> getEntityClass() {
		return StaffRole.class;
	}
	
	@Override
	public void saveStaffRole(StaffRole staffRole) {
		/**
		 * Validating Staff Role
		 */
		try{
			validate(staffRole);
		}catch(ValidationException e){
			throw new ValidationException();
		}
		
		/**
		 * Setting Audit details
		 */
		if(staffRole.getRoleId() == null || staffRole.getRoleId().isEmpty()){
			staffRole.setDateCreated(new DateTime());
			staffRole.setDateModified(new DateTime());	
		}else{
			staffRole.setDateModified(new DateTime());
		}
		
		logger.debug("Saving entity : {},{}",staffRole.getClass(),staffRole.toString());
		staffRoleDao.save(staffRole);
		logger.debug("Entity saved successfully");
	}

	@Override
	public StaffRole getStaffRoleById(String roleId) {
		return staffRoleDao.findByStaffRoleId(roleId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return staffRoleDao.buildDatatable(request);
	}

	@Override
	public List<StaffRole> getAllStaffRoles() {
		return staffRoleDao.findAllStaffRoles();
	}

}
