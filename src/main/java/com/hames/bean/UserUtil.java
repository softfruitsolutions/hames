package com.hames.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtil {
	
	private static final Subject currentUser = SecurityUtils.getSubject();

	public static boolean hasPermission(String permission){
		if(currentUser.isPermitted(permission)){
			return true;
		}
		return false;
	}
	
	public static boolean hasPermissions(String... permission){
		if(currentUser.isPermittedAll(permission)){
			return true;
		}
		return false;
	}
}
