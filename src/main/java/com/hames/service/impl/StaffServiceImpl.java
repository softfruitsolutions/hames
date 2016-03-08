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
import com.hames.service.StaffService;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.validator.StaffValidator;

@Service
public class StaffServiceImpl extends GenericService implements StaffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired private StaffDao staffDao;

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
			throw new ValidationException(e.getMessage());
		}
		
		//Setting Audit details
		staff.setAuditableDetails(staff.getStaffId());
		
		staffDao.save(staff);
	}

	@Override
	public Staff getStaffById(String staffId) {
		return staffDao.findById(staffId);
	}

	@Override
	public DatatableResponse getDatatable(DatatableRequest request) {
		return staffDao.getPagedDatatable(request);
	}

	@Override
	public List<Staff> getAllStaffs() {
		return staffDao.findAll();
	}

	@Override
	public boolean isStaffExists(String staffId) {
		return staffDao.isExists(staffId);
	}

	@Override
	public List<Staff> getAllActiveStaffs() {
		return staffDao.findAllActiveStaffs();
	}
	
}
