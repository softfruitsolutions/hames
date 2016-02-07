package com.hames.auth;

import java.util.LinkedHashSet;
import java.util.Set;

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

import com.hames.bean.RolePermission;
import com.hames.bean.Staff;
import com.hames.bean.UserContext;
import com.hames.db.HamesDataStore;
import com.hames.system.auth.UserAccount;
import com.mongodb.MongoException;

public class HamesRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(HamesRealm.class);
	private static final String USER_ACCOUNT_COLLECTION_NAME = "user_account";
	private static final String STAFF_COLLECTION_NAME = "staff";
	private static final String ROLE_COLLECTION_NAME = "role_permission";

	private static Set<String> permissions = new LinkedHashSet<String>();
	
	private HamesDataStore hamesDataStore;
	public void setHamesDataStore(HamesDataStore hamesDataStore) {
		this.hamesDataStore = hamesDataStore;
	}
	
	public HamesRealm() {
		setName("hamesRealm");
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		sai.setStringPermissions(permissions);
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
		
		//Fetching Permission
		RolePermission rolePermission = getRolePermissions(userAccount.getRoleId());
		setPermissions(rolePermission.getPermissions());
		
		UserContext.staff = getStaff(userAccount.getStaffId());
		
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

	private Staff getStaff(String staffId){
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("staffId").is(staffId));
			return hamesDataStore.findOne(query, Staff.class,STAFF_COLLECTION_NAME);
		}catch(MongoException e){
			logger.debug("Mongo Exception : {}",e);
			throw new MongoException(e.getMessage());
		}
	}
	
	private RolePermission getRolePermissions(String roleId){
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("roleId").is(roleId));
			return hamesDataStore.findOne(query, RolePermission.class,ROLE_COLLECTION_NAME);
		}catch(MongoException e){
			logger.debug("Mongo Exception : {}",e);
			throw new MongoException(e.getMessage());
		}
	}

	public static void setPermissions(Set<String> permissions) {
		HamesRealm.permissions = permissions;
	}
	
	
}
