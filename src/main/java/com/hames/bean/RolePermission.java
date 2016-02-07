package com.hames.bean;

import java.util.Set;

import org.springframework.data.annotation.Id;

import com.hames.enums.RolePermissionStatus;

public class RolePermission extends Audit {

	@Id
	private String roleId;
	private String roleName;
	private String roleDescription;
	private RolePermissionStatus status;
	private Set<String> permissions;
	
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
	public RolePermissionStatus getStatus() {
		return status;
	}
	public void setStatus(RolePermissionStatus status) {
		this.status = status;
	}
	public Set<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return "RolePermission [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", status=" + status
				+ ", permissions=" + permissions + "]";
	}
	
}
