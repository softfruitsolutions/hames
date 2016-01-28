package com.hames.dao;

import com.hames.system.auth.UserAccount;

public interface UserAccountDao {
	
	/**
	 * Save User Account
	 * @param userAccount
	 */
	void save(UserAccount userAccount);

}
