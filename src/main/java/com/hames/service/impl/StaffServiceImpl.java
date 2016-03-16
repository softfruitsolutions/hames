package com.hames.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.Staff;
import com.hames.dao.StaffDao;
import com.hames.service.GenericServiceImpl;
import com.hames.service.StaffService;
import com.hames.validator.StaffValidator;

@Service
public class StaffServiceImpl extends GenericServiceImpl<Staff> implements StaffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffDao staffDao;

	@Override
	public Validator getValidator() {
		return new StaffValidator();
	}
	
	@Override
	public String save(Staff staff) {
		
		//Validating Staff
		validate(staff);
		
		//Setting Audit details
		staff.setAuditableDetails(staff.getStaffId());
		
		return super.save(staff);
	}

	@Override
	public List<Staff> getAllActiveStaffs() {
		return staffDao.findAllActiveStaffs();
	}
	
}
