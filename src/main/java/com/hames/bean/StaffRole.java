package com.hames.bean;

import org.springframework.data.annotation.Id;

import com.hames.enums.StaffRoleStatusEnum;

public class StaffRole extends BaseBean {

	@Id
	private String roleId;
	private String roleName;
	private String roleDescription;
	private StaffRoleStatusEnum status;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public StaffRoleStatusEnum getStatus() {
		return status;
	}
	public void setStatus(StaffRoleStatusEnum status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "StaffRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", status=" + status
				+ "]";
	}
	
}
