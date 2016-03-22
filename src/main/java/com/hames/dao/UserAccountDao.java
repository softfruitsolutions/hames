package com.hames.dao;

import com.hames.mongo.GenericDao;
import com.hames.system.auth.UserAccount;

public interface UserAccountDao extends GenericDao<UserAccount> {
	
	/**
	 * Is username exists
	 * @return
	 */
	public boolean isUsernameExists(String username);
	
	/**
	 * Check an User account exists for Staff
	 * @param staffId
	 * @return
	 */
	public boolean checkUAExistsForStaff(String staffId);

}
