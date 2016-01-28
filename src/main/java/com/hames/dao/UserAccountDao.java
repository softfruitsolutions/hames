package com.hames.dao;

import java.util.List;

import com.hames.system.auth.UserAccount;

public interface UserAccountDao {
	
	/**
	 * Save User Account
	 * @param userAccount
	 */
	void save(UserAccount userAccount);
	
	/**
	 * Get User Accounts
	 * @return
	 */
	List<UserAccount> getUserAccounts();
	
	/**
	 * Is Username exists
	 * @return
	 */
	boolean isUsernameExists(String username);
	
	/**
	 * Check an User account exists for Staff
	 * @param staffId
	 * @return
	 */
	boolean checkUserAccountExistsForStaff(String staffId);

}
