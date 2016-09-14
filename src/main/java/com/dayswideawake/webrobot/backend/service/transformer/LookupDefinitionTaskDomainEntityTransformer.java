package com.dayswideawake.webrobot.backend.service.transformer;

import java.util.Date;

import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.backend.repository.entity.LookupDefinitionTaskEntity;

public class LookupDefinitionTaskDomainEntityTransformer {

	public LookupDefinitionTask entityToDomain(LookupDefinitionTaskEntity entity){
		return new LookupDefinitionTask.Builder(entity.getLookupDefinitionId(), entity.getIntervalInSeconds())
				.lastLookupAt(new Date(entity.getLastLookupAt()))
				.isQueuedNow(entity.isQueuedNow())
				.build();
	}
	
}
