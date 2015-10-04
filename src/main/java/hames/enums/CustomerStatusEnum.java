package hames.enums;

public enum CustomerStatusEnum {
	
	ACTIVE_CUSTOMER(30,"Active"),
	INACTIVE_CUSTOMER(31,"In-Active");
	
	private int value;
	private String text;
	
	private CustomerStatusEnum(int value,String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}
	
	public String getText() {
		return text;
	}

	public static CustomerStatusEnum findEnum(int value){
		for (CustomerStatusEnum status : CustomerStatusEnum.values()) {
			if(status.getValue() == value){
				return status;
			}
		}
		return null;
	}
}
