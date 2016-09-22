package com.dayswideawake.webrobot.backend.domain;

import java.util.Date;
import java.util.Optional;

public class LookupJob {

	private Optional<Long> id;
	private Long lookupDefinitionId;
	private Long intervalInSeconds;
	private Optional<Date> lastLookupAt;
	private Boolean isQueuedNow;

	private LookupJob(Builder builder) {
		id = builder.id;
		lookupDefinitionId = builder.lookupDefinitionId;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt;
		isQueuedNow = builder.isQueuedNow.orElse(false);
	}

	public Optional<Long> getId() {
		return id;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public Optional<Date> getLastLookupAt() {
		return lastLookupAt;
	}

	public Boolean isQueuedNow() {
		return isQueuedNow;
	}

	@Override
	public String toString() {
		return "LookupJob [id=" + id + ", lookupDefinitionId=" + lookupDefinitionId + ", intervalInSeconds=" + intervalInSeconds + ", lastLookupAt=" + lastLookupAt + ", isQueuedNow=" + isQueuedNow + "]";
	}

	public static class Builder {
		private Optional<Long> id;
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Optional<Date> lastLookupAt;
		private Optional<Boolean> isQueuedNow;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
			this.lastLookupAt = Optional.empty();
			this.isQueuedNow = Optional.empty();
		}

		public Builder id(Long id) {
			this.id = Optional.ofNullable(id);
			return this;
		}

		public Builder lastLookupAt(Date lastLookupAt) {
			this.lastLookupAt = Optional.ofNullable(lastLookupAt);
			return this;
		}

		public Builder lastLookupAt(Long lastLookupAt) {
			this.lastLookupAt = Optional.ofNullable(lastLookupAt).map(o -> new Date(o));
			return this;
		}

		public Builder isQueuedNow(Boolean isQueuedNow) {
			this.isQueuedNow = Optional.ofNullable(isQueuedNow);
			return this;
		}

		public LookupJob build() {
			return new LookupJob(this);
		}

	}

}
