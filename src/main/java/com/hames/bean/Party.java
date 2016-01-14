package com.hames.bean;

import org.springframework.data.annotation.Id;

import com.hames.enums.PartyStatusEnum;
import com.hames.enums.PartyTypeEnum;

public class Party extends BaseBean{

	@Id
	private String partyId;
	private PartyTypeEnum partyType;
	private PartyStatusEnum status;
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public PartyTypeEnum getPartyType() {
		return partyType;
	}
	public void setPartyType(PartyTypeEnum partyType) {
		this.partyType = partyType;
	}
	public PartyStatusEnum getStatus() {
		return status;
	}
	public void setStatus(PartyStatusEnum status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", partyType=" + partyType
				+ ", status=" + status + "]";
	}
	
}
