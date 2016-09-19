package com.dayswideawake.webrobot.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.domain.LookupJob;
import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.Channels;
import com.dayswideawake.webrobot.messaging.model.LookupDefinitionCreatedEventMessage;
import com.dayswideawake.webrobot.messaging.transformer.LookupDefinitionTaskDomainMessageTransformer;

@Component
public class NewLookupDefinitionHandler {

	private LookupDefinitionTaskService lookupDefinitionTaskService;
	private LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer;

	@Autowired
	public NewLookupDefinitionHandler(LookupDefinitionTaskService lookupDefinitionTaskService, LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer) {
		this.lookupDefinitionTaskService = lookupDefinitionTaskService;
		this.domainMessageTransformer = domainMessageTransformer;
	}

	@StreamListener(Channels.CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	@Loggable
	public void handle(LookupDefinitionCreatedEventMessage message) {
		LookupJob task = domainMessageTransformer.createdEventMessageToDomain(message);
		lookupDefinitionTaskService.addLookupDefinitionTask(task);
	}

}
