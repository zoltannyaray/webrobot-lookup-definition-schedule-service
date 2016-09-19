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

	private LookupDefinitionTaskService lookupJobService;

	@Autowired
	public NewLookupHandler(LookupDefinitionTaskService lookupJobService) {
		this.lookupJobService = lookupJobService;
	}
	
	@StreamListener(Channels.CHANNEL_INPUT_NEW_LOOKUPS)
	@Loggable
	private void onNewLookup(LookupCreatedMessage message) {
		Long lookupJobId = message.getLookupJobId();
		Date lookupTime = new Date(message.getLookupTime());
		lookupJobService.markTaskLastLookupAt(lookupJobId, lookupTime);
	}
	
	
	
	
}
