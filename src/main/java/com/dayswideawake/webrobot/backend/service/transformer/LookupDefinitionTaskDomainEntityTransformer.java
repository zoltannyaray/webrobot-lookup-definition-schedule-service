package com.dayswideawake.webrobot.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.backend.domain.LookupJob;
import com.dayswideawake.webrobot.backend.repository.entity.LookupJobEntity;

@Component
public class LookupDefinitionTaskDomainEntityTransformer {

	public LookupJob entityToDomain(LookupJobEntity entity) {
		return new LookupJob.Builder(entity.getLookupDefinitionId(), entity.getIntervalInSeconds())
				.id(entity.getId())
				.lastLookupAt(entity.getLastLookupAt())
				.isQueuedNow(entity.isQueuedNow())
				.build();
	}

	public LookupJobEntity domainToEntity(LookupJob domain) {
		return new LookupJobEntity.Builder(domain.getLookupDefinitionId(), domain.getIntervalInSeconds())
				.lastLookupAt(domain.getLastLookupAt().orElse(null))
				.isQueuedNow(domain.isQueuedNow())
				.build();
	}

}
