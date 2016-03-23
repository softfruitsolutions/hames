package com.hames.party.model;

import org.springframework.data.annotation.Id;

import com.hames.bean.Audit;
import com.hames.enums.PartyStatus;
import com.hames.enums.PartyType;

public class Party extends Audit{

	@Id
	private String partyId;
	private PartyType partyType;
	private PartyStatus status;
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public PartyType getPartyType() {
		return partyType;
	}
	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}
	public PartyStatus getStatus() {
		return status;
	}
	public void setStatus(PartyStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", partyType=" + partyType
				+ ", status=" + status + "]";
	}
	
}
