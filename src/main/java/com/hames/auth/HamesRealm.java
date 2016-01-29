package com.hames.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hames.db.HamesDataStore;
import com.hames.system.auth.UserAccount;
import com.mongodb.MongoException;

public class HamesRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(HamesRealm.class);
	private static final String USER_ACCOUNT_COLLECTION_NAME = "user_account";

	private HamesDataStore hamesDataStore;
	public void setHamesDataStore(HamesDataStore hamesDataStore) {
		this.hamesDataStore = hamesDataStore;
	}
	
	public HamesRealm() {
		setName("hamesRealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("Entering authorization info");
		System.out.println(principals);
		
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		
		System.out.println("Principals exists");
		String username = (String) getAvailablePrincipal(principals);
		UserAccount userAccount = getUserAccount(username);
		
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		sai.setStringPermissions(userAccount.getRolePermission().getPermissions());
		return sai;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		char[] password = upToken.getPassword();

		UserAccount userAccount = getUserAccount(username);
		
		if(userAccount == null){
			logger.debug("Username doesn't exists");
			throw new IncorrectCredentialsException("Username doesn't exists");
		}
		
		DefaultPasswordService passwordService = new DefaultPasswordService();
		if(!passwordService.passwordsMatch(password, userAccount.getPassword())){
			logger.debug("Incorrect Password. Authentication failure.");
			throw new IncorrectCredentialsException("Oops. Incorrect Password. Please try again");
		}
		
		return new SimpleAuthenticationInfo(upToken.getUsername(),upToken.getPassword(),getName());
	}
	
	/**
	 * Fetching UserAccount by Username
	 * @param username
	 * @return
	 */
	private UserAccount getUserAccount(String username){
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(username));
			return hamesDataStore.findOne(query, UserAccount.class,USER_ACCOUNT_COLLECTION_NAME);
		}catch(MongoException e){
			logger.debug("Mongo exception : {}",e);
			throw new MongoException(e.getMessage());
		}
	}

}
