package com.dayswideawake.webrobot.backend.repository.entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LookupJobEntity {
	@Id
	@GeneratedValue
	private Long id;
	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Long lastLookupAt;
	private Boolean isQueuedNow;

	public LookupJobEntity() {
	}

	private LookupJobEntity(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt.orElse(null);
		isQueuedNow = builder.isQueuedNow.orElse(false);
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
	
	public void setLastLookupAt(Long lastLookupAt) {
		this.lastLookupAt = lastLookupAt;
	}

	public Boolean isQueuedNow() {
		return isQueuedNow;
	}

	public void isQueuedNow(Boolean isQueuedNow) {
		this.isQueuedNow = isQueuedNow;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Optional<Long> lastLookupAt;
		private Optional<Boolean> isQueuedNow;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder lastLookupAt(Long lastLookupAt) {
			this.lastLookupAt = Optional.ofNullable(lastLookupAt);
			return this;
		}

		public Builder lastLookupAt(Date lastLookupAt) {
			this.lastLookupAt = Optional.ofNullable(lastLookupAt).map(o -> o.getTime());
			return this;
		}

		public Builder isQueuedNow(Boolean isQueuedNow) {
			this.isQueuedNow = Optional.ofNullable(isQueuedNow);
			return this;
		}

		public LookupJobEntity build() {
			return new LookupJobEntity(this);
		}
	}
}
