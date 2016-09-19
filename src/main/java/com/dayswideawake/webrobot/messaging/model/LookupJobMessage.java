package com.dayswideawake.webrobot.messaging.model;

public class LookupJobMessage {

	private Long lookupJobId;
	private Long lookupDefinitionId;

	private LookupJobMessage(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
	}

	public Long getLookupJobId() {
		return lookupJobId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;

		public Builder(Long lookupJobId, Long lookupDefinitionId) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
		}

		public LookupJobMessage build() {
			return new LookupJobMessage(this);
		}
	}

}
