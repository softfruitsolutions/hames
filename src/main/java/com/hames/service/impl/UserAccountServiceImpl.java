package com.hames.service.impl;

import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.dao.UserAccountDao;
import com.hames.service.GenericService;
import com.hames.service.UserAccountService;
import com.hames.system.auth.UserAccount;

@Service
public class UserAccountServiceImpl extends GenericService implements UserAccountService {

	@Autowired private UserAccountDao userAccountDao;
	private PasswordService passwordService;
	
	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		return UserAccount.class;
	}

	@Override
	public void save(UserAccount userAccount) {
		
		String encryptedPassword = passwordService.encryptPassword(userAccount.getPassword());
		userAccount.setPassword(encryptedPassword);
		
		userAccountDao.save(userAccount);
	}

}
