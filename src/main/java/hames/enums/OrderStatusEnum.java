package hames.enums;

public enum OrderStatusEnum {
	
	DRAFT(40),
	CREATED(41),
	IN_PROGRESS(42),
	COMPLETED(43),
	DELIVERED(44);
	
	private int value;
	
	private OrderStatusEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static OrderStatusEnum findEnum(int value){
		for (OrderStatusEnum status : OrderStatusEnum.values()) {
			if(status.getValue() == value){
				return status;
			}
		}
		return null;
	}
}
