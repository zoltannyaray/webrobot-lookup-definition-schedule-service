package com.dayswideawake.webrobot.backend.service.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.backend.repository.entity.LookupDefinitionTaskEntity;

@Component
public class LookupDefinitionTaskDomainEntityTransformer {

	public LookupDefinitionTask entityToDomain(LookupDefinitionTaskEntity entity) {
		return new LookupDefinitionTask.Builder(entity.getLookupDefinitionId(), entity.getIntervalInSeconds())
				.id(entity.getId())
				.lastLookupAt(entity.getLastLookupAt())
				.isQueuedNow(entity.isQueuedNow())
				.build();
	}

	public LookupDefinitionTaskEntity domainToEntity(LookupDefinitionTask domain) {
		return new LookupDefinitionTaskEntity.Builder(domain.getLookupDefinitionId(), domain.getIntervalInSeconds())
				.lastLookupAt(domain.getLastLookupAt().orElse(null))
				.isQueuedNow(domain.isQueuedNow())
				.build();
	}

}
