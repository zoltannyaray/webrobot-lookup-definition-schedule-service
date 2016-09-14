package com.dayswideawake.webrobot.backend.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LookupDefinitionTaskEntity {
	@Id
	@GeneratedValue
	private Long id;
	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Long lastLookupAt;
	private Boolean isQueuedNow;

	public LookupDefinitionTaskEntity() {
	}

	private LookupDefinitionTaskEntity(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt;
		isQueuedNow = builder.isQueuedNow;
	}

	public Long getId() {
		return id;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public Long getLastLookupAt() {
		return lastLookupAt;
	}
	
	public Boolean isQueuedNow() {
		return isQueuedNow;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Long lastLookupAt;
		private Boolean isQueuedNow;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder lastLookupAt(Long lastLookupAt) {
			this.lastLookupAt = lastLookupAt;
			return this;
		}
		
		public Builder isQueuedNow(Boolean isQueuedNow) {
			this.isQueuedNow = isQueuedNow;
			return this;
		}

		public LookupDefinitionTaskEntity build() {
			return new LookupDefinitionTaskEntity(this);
		}
	}
}
