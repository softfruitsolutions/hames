package hames.core.enums;

public enum AuditEnum {

	STAFF_CREATE(100),
	STAFF_DELETE(101),
	STAFF_UPDATE(102),
	
	STAFF_ROLE_CREATE(110),
	STAFF_ROLE_UPDATE(111),
	STAFF_ROLE_DELETE(112);
	
	private int value;
	
	private AuditEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
