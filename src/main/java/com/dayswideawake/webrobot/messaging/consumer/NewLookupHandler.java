package com.dayswideawake.webrobot.messaging.consumer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.Channels;
import com.dayswideawake.webrobot.messaging.model.LookupCreatedMessage;

@Component
public class NewLookupHandler {

	private LookupDefinitionTaskService lookupDefinitionTaskService;

	@Autowired
	public NewLookupHandler(LookupDefinitionTaskService lookupDefinitionTaskService) {
		this.lookupDefinitionTaskService = lookupDefinitionTaskService;
	}
	
	@StreamListener(Channels.CHANNEL_INPUT_NEW_LOOKUPS)
	@Loggable
	public void onNewLookup(LookupCreatedMessage message) {
		Long lookupJobId = message.getLookupJobId();
		Date lookupTime = new Date(message.getLookupTime());
		lookupDefinitionTaskService.markTaskLastLookupAt(lookupJobId, lookupTime);
	}
	
	
	
	
}
