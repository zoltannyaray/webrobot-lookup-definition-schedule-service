package com.dayswideawake.backend.domain;

import java.util.Date;

public class LookupDefinitionTask {

	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Date lastLookupAt;
	private Boolean isQueuedNow;

	private LookupDefinitionTask(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt;
		isQueuedNow = builder.isQueuedNow;
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

	public Boolean isQueuedNow() {
		return isQueuedNow;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Date lastLookupAt;
		private Boolean isQueuedNow;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder lastLookupAt(Date lastLookupAt) {
			this.lastLookupAt = lastLookupAt;
			return this;
		}
		
		public Builder isQueuedNow(Boolean isQueuedNow){
			this.isQueuedNow = isQueuedNow;
			return this;
		}

		public LookupDefinitionTask build() {
			return new LookupDefinitionTask(this);
		}

	}

}
