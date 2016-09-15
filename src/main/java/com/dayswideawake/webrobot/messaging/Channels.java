package com.dayswideawake.webrobot.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	String CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS = "new-lookup-definitions";
	String CHANNEL_OUTPUT_LOOKUP_TASKS = "lookup-tasks";
	
	@Input(CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	SubscribableChannel newLookupDefinitions();
	
	@Output(CHANNEL_OUTPUT_LOOKUP_TASKS)
	MessageChannel lookupTasks();
	
}
