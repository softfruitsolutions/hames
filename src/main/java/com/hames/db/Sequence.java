package com.hames.db;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Sequence {
	
	public static final String SALE_ORDER_SEQUENCE = "sale_order";

	@Id
	private String sequenceName;
	private Long sequence;
	
	public Sequence() {
	}
	
	public Sequence(String sequenceName, Long sequence) {
		this.sequenceName = sequenceName;
		this.sequence = sequence;
	}
	
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "Sequence [sequenceName=" + sequenceName + ", sequence="
				+ sequence + "]";
	}
}
