package com.hames.auth;

import java.util.Arrays;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

@Service
public class HamesRealm extends AuthorizingRealm {

	public HamesRealm() {
		setName("hamesRealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		
		char[] savedPassword = "admin".toCharArray();
		char[] password = upToken.getPassword();
		
		if(!Arrays.equals(savedPassword, password)){
			System.out.println("Password Incorrect");
			throw new IncorrectCredentialsException("Password Incorrect");
		}
		return new SimpleAuthenticationInfo(username,password,getName());
	}

}
