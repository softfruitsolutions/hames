package com.hames.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="sequence")
public class Sequence {
	
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
