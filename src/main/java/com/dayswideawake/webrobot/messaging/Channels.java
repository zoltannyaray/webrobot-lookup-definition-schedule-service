package com.dayswideawake.webrobot.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	String CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS = "new-lookup-definitions";
	String CHANNEL_INPUT_NEW_LOOKUPS = "new-lookups";
	String CHANNEL_OUTPUT_LOOKUP_JOBS = "lookup-jobs";
	
	@Input(CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	SubscribableChannel newLookupDefinitions();
	
	@Input(CHANNEL_INPUT_NEW_LOOKUPS)
	SubscribableChannel newLookups();
	
	@Output(CHANNEL_OUTPUT_LOOKUP_JOBS)
	MessageChannel lookupJobs();
	
}
