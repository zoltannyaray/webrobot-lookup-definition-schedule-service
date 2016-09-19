package com.dayswideawake.webrobot.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupCreatedMessage.Builder.class)
public class LookupCreatedMessage {

	private Long lookupJobId;
	private Long lookupDefinitionId;
	private Long lookupTime;

	private LookupCreatedMessage(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
	}

	public Long getLookupJobId() {
		return lookupJobId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getLookupTime() {
		return lookupTime;
	}

	@Override
	public String toString() {
		return "LookupCreatedMessage [lookupJobId=" + lookupJobId + ", lookupDefinitionId=" + lookupDefinitionId + ", lookupTime=" + lookupTime + "]";
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;
		private Long lookupTime;

		@JsonCreator
		public Builder(@JsonProperty("lookupJobId") Long lookupJobId, @JsonProperty("lookupDefinitionId") Long lookupDefinitionId, @JsonProperty("lookupTime") Long lookupTime) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
		}

		public LookupCreatedMessage build() {
			return new LookupCreatedMessage(this);
		}
	}

}
