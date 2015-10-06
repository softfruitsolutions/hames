package hames.enums;

public enum StaffStatusEnum {
	
	ACTIVE_STAFF(10),
	INACTIVE_STAFF(11);
	
	private int value;
	
	private StaffStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static StaffStatusEnum findEnum(int value){
		for (StaffStatusEnum status : StaffStatusEnum.values()) {
			if(status.getValue() == value){
				return status;
			}
		}
		return null;
	}
}
