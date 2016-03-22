package com.hames.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="sequence")
public class Sequence {
	
	@Id
	private String sequenceName;
	private String sequenceCode;
	private Long sequence;
	
	public Sequence() {
	}
	
	public Sequence(String sequenceName, String sequenceCode, Long sequence) {
		this.sequenceName = sequenceName;
		this.sequence = sequence;
		this.sequenceCode = sequenceCode;
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
	public String getSequenceCode() {
		return sequenceCode;
	}
	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	@Override
	public String toString() {
		return "Sequence [sequenceName=" + sequenceName + ", sequence="
				+ sequence + "]";
	}
	
	public static final String SALE_ORDER_SEQUENCE = "sale_order";
}
