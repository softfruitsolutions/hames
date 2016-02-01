package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.Staff;
import com.hames.dao.StaffDao;
import com.hames.exception.ValidationException;
import com.hames.service.GenericService;
import com.hames.service.RolePermissionService;
import com.hames.service.StaffService;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.validator.StaffValidator;

@Service
public class StaffServiceImpl extends GenericService implements StaffService {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffDao staffDao;
	@Autowired
	private RolePermissionService staffRoleService;

	@Override
	public Validator getValidator() {
		return new StaffValidator();
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Staff.class;
	}
	
	@Override
	public void saveStaff(Staff staff) {
		
		/**
		 * validating Staff
		 */
		try{
			validate(staff);
		}catch(ValidationException e){
			throw new ValidationException();
		}
		
		//Setting Audit details
		staff.setAuditableDetails(staff.getStaffId());
		
		logger.debug("Saving entity : {},{}",staff.getClass(),staff.toString());
		staffDao.save(staff);
		logger.debug("Entity saved successfully");
	}

	@Override
	public Staff getStaffById(String staffId) {
		return staffDao.findByStaffId(staffId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return staffDao.buildDatatable(request);
	}

	@Override
	public List<Staff> getAllStaffs() {
		return staffDao.findAllStaffs();
	}

	@Override
	public boolean isStaffExists(String staffId) {
		return staffDao.isStaffExists(staffId);
	}

	@Override
	public List<Staff> getAllActiveStaffs() {
		return staffDao.findAllActiveStaffs();
	}
	
}
