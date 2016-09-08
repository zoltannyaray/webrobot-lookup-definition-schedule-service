package com.dayswideawake.backend.repository.entity;

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

	public LookupDefinitionTaskEntity() {
	}

	private LookupDefinitionTaskEntity(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		intervalInSeconds = builder.intervalInSeconds;
		lastLookupAt = builder.lastLookupAt;
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

	public static class Builder {
		private Long lookupDefinitionId;
		private Long intervalInSeconds;
		private Long lastLookupAt;

		public Builder(Long lookupDefinitionId, Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.intervalInSeconds = intervalInSeconds;
		}

		public Builder lastLookupAt(Long lastLookupAt) {
			this.lastLookupAt = lastLookupAt;
			return this;
		}

		public LookupDefinitionTaskEntity build() {
			return new LookupDefinitionTaskEntity(this);
		}
	}
}
