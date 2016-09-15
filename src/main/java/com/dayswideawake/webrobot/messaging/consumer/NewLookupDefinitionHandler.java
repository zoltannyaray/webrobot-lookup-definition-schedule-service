package com.dayswideawake.webrobot.messaging.consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.Channels;
import com.dayswideawake.webrobot.messaging.model.LookupDefinitionCreatedEventMessage;
import com.dayswideawake.webrobot.messaging.transformer.LookupDefinitionTaskDomainMessageTransformer;

@Component
public class NewLookupDefinitionHandler {

	private LookupDefinitionTaskService lookupDefinitionTaskService;
	private LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer;
	private static Logger LOGGER = Logger.getLogger(NewLookupDefinitionHandler.class.getName());

	@Autowired
	public NewLookupDefinitionHandler(LookupDefinitionTaskService lookupDefinitionTaskService, LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer) {
		this.lookupDefinitionTaskService = lookupDefinitionTaskService;
		this.domainMessageTransformer = domainMessageTransformer;
	}

	@StreamListener(Channels.CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	@Loggable
	public void handle(LookupDefinitionCreatedEventMessage message) {
		LOGGER.log(Level.INFO, "New lookup definition message received: " + message.toString());
		LookupDefinitionTask task = domainMessageTransformer.createdEventMessageToDomain(message);
		lookupDefinitionTaskService.addLookupDefinitionTask(task);
	}

}
