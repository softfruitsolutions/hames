package hames.enums;

public enum StaffRoleStatusEnum {

	ACTIVE_STAFFROLE(20),
	INACTIVE_STAFFROLE(21);
	
	private int value;
	
	private StaffRoleStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static StaffRoleStatusEnum findEnum(int value){
		for (StaffRoleStatusEnum status : StaffRoleStatusEnum.values()) {
			if(status.getValue() == value){
				return status;
			}
		}
		return null;
	}
	
}
