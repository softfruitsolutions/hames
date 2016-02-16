package com.hames.service;

import java.util.List;

import com.hames.system.auth.UserAccount;

public interface UserAccountService {
	
	/**
	 * Save User Account
	 * @param userAccount
	 */
	public void save(UserAccount userAccount);
	
	/**
	 * Get User Accounts
	 * @return
	 */
	public List<UserAccount> getUserAccounts();
	
	/**
	 * Get User Accounts. Load all fields. Eg: Staff, Role etc..
	 * @param loadAll
	 * @return
	 */
	public List<UserAccount> getUserAccounts(Boolean allDataRequired);
	

}
