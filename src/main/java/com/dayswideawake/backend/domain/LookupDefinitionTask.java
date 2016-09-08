package com.dayswideawake.backend.domain;

import java.util.Date;

public class LookupDefinitionTask {

	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Date lastLookupAt;

	private LookupDefinitionTask(Builder builder) {
		this.lookupDefinitionId = builder.lookupDefinitionId;
		this.intervalInSeconds = builder.intervalInSeconds;
		this.lastLookupAt = builder.lastLookupAt;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public Date getLastLookupAt() {
		return new Date(lastLookupAt.getTime());
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Date lastLookupAt;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder lastLookupAt(Date lastLookupAt) {
			this.lastLookupAt = lastLookupAt;
			return this;
		}

		public LookupDefinitionTask build() {
			return new LookupDefinitionTask(this);
		}

	}

}
