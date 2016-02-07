package com.hames.system.auth;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import com.hames.bean.Audit;
import com.hames.bean.RolePermission;

public class UserAccount extends Audit{

	@Indexed(unique=true)
	private String username;
	private String password;
	
	@Indexed(unique=true)
	private String staffId;
	private String roleId;
	
	private String staffName;
	private String roleName;
	
	@Transient
	private RolePermission rolePermission;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public RolePermission getRolePermission() {
		return rolePermission;
	}
	public void setRolePermission(RolePermission rolePermission) {
		this.rolePermission = rolePermission;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + ", staffId=" + staffId + ", roleId="
				+ roleId + ", staffName=" + staffName + ", roleName=" + roleName + ", rolePermission=" + rolePermission
				+ "]";
	}

}
