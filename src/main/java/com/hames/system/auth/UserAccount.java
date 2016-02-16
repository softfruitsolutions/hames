package com.hames.system.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import com.hames.bean.Audit;
import com.hames.bean.RolePermission;
import com.hames.bean.Staff;

public class UserAccount extends Audit{

	@Id
	private String accountId;
	
	@Indexed(unique=true)
	private String username;
	private String password;
	
	@Indexed(unique=true)
	private String staffId;
	private String roleId;
	
	@Transient
	private Staff staff;
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
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", username=" + username + ", password=" + password
				+ ", staffId=" + staffId + ", roleId=" + roleId + ", staff=" + staff + ", rolePermission="
				+ rolePermission + "]";
	}

}
