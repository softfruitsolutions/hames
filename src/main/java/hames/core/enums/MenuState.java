package hames.core.enums;

public enum MenuState {
	
	ACTIVE(1),
	INACTIVE(0);
	
	private int value;
	
	private MenuState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
