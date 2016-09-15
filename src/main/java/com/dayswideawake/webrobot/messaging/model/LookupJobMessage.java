package com.dayswideawake.webrobot.messaging.model;

public class LookupJobMessage {

	private Long lookupDefinitionId;

	private LookupJobMessage(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public static class Builder {
		private Long lookupDefinitionId;

		public Builder(Long lookupDefinitionId) {
			this.lookupDefinitionId = lookupDefinitionId;
		}

		public LookupJobMessage build() {
			return new LookupJobMessage(this);
		}
	}

}
