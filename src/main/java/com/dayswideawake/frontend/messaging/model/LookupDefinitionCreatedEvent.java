package com.dayswideawake.frontend.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LookupDefinitionCreatedEvent {

	private Long lookupDefinitionId;

	@JsonCreator
	public LookupDefinitionCreatedEvent() {
	}

	public LookupDefinitionCreatedEvent(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	public void setLookupDefinitionId(Long lookupDefinitionId) {
		this.lookupDefinitionId = lookupDefinitionId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	@Override
	public String toString() {
		return "LookupDefinitionCreatedEvent [lookupDefinitionId=" + lookupDefinitionId + "]";
	}
	
}
