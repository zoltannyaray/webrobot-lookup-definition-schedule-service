package com.dayswideawake.webrobot.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupDefinitionCreatedEventMessage.Builder.class)
public class LookupDefinitionCreatedEventMessage {

	private Long lookupDefinitionId;
	private Long accountId;
	private Long intervalInSeconds;

	private LookupDefinitionCreatedEventMessage(Builder builder) {
		this.lookupDefinitionId = builder.lookupDefinitionId;
		this.accountId = builder.accountId;
		this.intervalInSeconds = builder.intervalInSeconds;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	@Override
	public String toString() {
		return "LookupDefinitionCreatedEvent [lookupDefinitionId=" + lookupDefinitionId + ", accountId=" + accountId + ", intervalInSeconds=" + intervalInSeconds + "]";
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long accountId;
		private Long intervalInSeconds;

		@JsonCreator
		public Builder(@JsonProperty("lookupDefinitionId") Long lookupDefinitionId, @JsonProperty("intervalInSeconds") Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder withAccountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		public LookupDefinitionCreatedEventMessage build() {
			return new LookupDefinitionCreatedEventMessage(this);
		}

	}

}
