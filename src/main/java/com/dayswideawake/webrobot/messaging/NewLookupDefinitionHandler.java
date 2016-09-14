package com.dayswideawake.webrobot.messaging;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.model.LookupDefinitionCreatedEvent;

@Component
public class NewLookupDefinitionHandler {

	private LookupDefinitionTaskService lookupDefinitionTaskService;
	private static Logger LOGGER = Logger.getLogger(NewLookupDefinitionHandler.class.getName());

	@Autowired
	public NewLookupDefinitionHandler(LookupDefinitionTaskService lookupDefinitionTaskService) {
		this.lookupDefinitionTaskService = lookupDefinitionTaskService;
	}
	
	@StreamListener(Channels.CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	public void handle(LookupDefinitionCreatedEvent event){
		LOGGER.log(Level.INFO, "New lookup definition message received: " + event.toString());
	}
	
	
}
