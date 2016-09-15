package com.dayswideawake.webrobot.messaging.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.messaging.model.LookupDefinitionCreatedEventMessage;
import com.dayswideawake.webrobot.messaging.model.LookupJobMessage;

@Component
public class LookupDefinitionTaskDomainMessageTransformer {

	public LookupDefinitionTask createdEventMessageToDomain(LookupDefinitionCreatedEventMessage message){
		return new LookupDefinitionTask.Builder(message.getLookupDefinitionId(), message.getIntervalInSeconds())
				.isQueuedNow(false)
				.build();
	}
	
	public LookupJobMessage domainTaskToJobMessage(LookupDefinitionTask domainTask){
		return new LookupJobMessage.Builder(domainTask.getLookupDefinitionId()).build();
	}
	
	
}
