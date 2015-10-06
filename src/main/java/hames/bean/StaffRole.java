package hames.bean;

import hames.core.bean.BaseBean;

public class StaffRole extends BaseBean {

	private Long roleId;
	private String roleName;
	private String roleDescription;
	private Integer status;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "StaffRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", status=" + status
				+ "]";
	}
	
}
