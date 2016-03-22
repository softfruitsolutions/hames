package com.hames.service.impl;

import java.util.List;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.bean.Staff;
import com.hames.dao.UserAccountDao;
import com.hames.exception.RolePermissionException;
import com.hames.exception.StaffException;
import com.hames.service.GenericServiceImpl;
import com.hames.service.RolePermissionService;
import com.hames.service.StaffService;
import com.hames.service.UserAccountService;
import com.hames.system.auth.UserAccount;
import com.hames.validator.UserAccountValidator;

@Service
public class UserAccountServiceImpl extends GenericServiceImpl<UserAccount> implements UserAccountService {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private StaffService staffService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Override
	public Validator getValidator() {
		return new UserAccountValidator();
	}

	@Override
	public String save(UserAccount userAccount) {
		//Validating user account
		validate(userAccount);
		
		if(userAccountDao.isUsernameExists(userAccount.getUsername())){
			logger.debug("Username already present. Opeartion Aborted");
			throw new RolePermissionException("Someone already has that username. Try another?");
		}
		
		if(userAccountDao.checkUAExistsForStaff(userAccount.getStaffId())){
			logger.debug("UserAccount already exists for staff. Opeartion Aborted");
			throw new RolePermissionException("UserAccount already exists for staff");
		}
		
		//TODO Check staff status. in-active staff can't apply role
		Staff staff = staffService.getById(userAccount.getStaffId());
		if(staff == null){
			logger.debug("Staff not found. Aborting operation");
			throw new StaffException("Invalid Staff");
		}
		
		if(!rolePermissionService.isExists(userAccount.getRoleId())){
			logger.debug("Role not found : {}",userAccount.getRolePermission().getRoleId());
			throw new RolePermissionException("Role doesn't exists.!");
		}
		
		if(userAccount.getAccountId() == null || userAccount.getAccountId().isEmpty()){
			DefaultPasswordService passwordService = new DefaultPasswordService();
			String encryptedPassword = passwordService.encryptPassword(userAccount.getPassword());
			userAccount.setPassword(encryptedPassword);
		}
		
		//Setting details
		userAccount.setAudit(userAccount.getAccountId());
		
		return super.save(userAccount);
	}

	@Override
	public List<UserAccount> getAll() {
		List<UserAccount> userAccounts = userAccountDao.findAll();
		if(userAccounts != null && !userAccounts.isEmpty()){
			for (UserAccount ua : userAccounts) {
				ua.setStaff(staffService.getById(ua.getStaffId()));
				ua.setRolePermission(rolePermissionService.getById(ua.getRoleId()));
			}
		}
		return userAccounts;
	}

}
