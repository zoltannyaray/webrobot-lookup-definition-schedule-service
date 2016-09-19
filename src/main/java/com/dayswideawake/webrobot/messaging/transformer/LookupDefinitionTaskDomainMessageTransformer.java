package com.dayswideawake.webrobot.messaging.transformer;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.backend.domain.LookupJob;
import com.dayswideawake.webrobot.messaging.model.LookupDefinitionCreatedEventMessage;
import com.dayswideawake.webrobot.messaging.model.LookupJobMessage;

@Component
public class LookupDefinitionTaskDomainMessageTransformer {

	public LookupJob createdEventMessageToDomain(LookupDefinitionCreatedEventMessage message){
		return new LookupJob.Builder(message.getLookupDefinitionId(), message.getIntervalInSeconds())
				.isQueuedNow(false)
				.build();
	}
	
	public LookupJobMessage domainTaskToJobMessage(LookupJob domainTask){
		Optional<Long> lookupJobId = domainTask.getId();
		if (!lookupJobId.isPresent()) {
			throw new IllegalArgumentException("Lookup job must have an ID to convert it to message");
		}
		Long lookupDefinitionId = domainTask.getLookupDefinitionId();
		return new LookupJobMessage.Builder(lookupJobId.get(), lookupDefinitionId).build();
	}
	
	
}
