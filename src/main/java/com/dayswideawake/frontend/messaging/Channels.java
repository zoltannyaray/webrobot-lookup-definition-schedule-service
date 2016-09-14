package com.dayswideawake.frontend.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	String CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS = "new-lookup-definitions";
	
	@Input(CHANNEL_INPUT_NEW_LOOKUP_DEFINITIONS)
	SubscribableChannel newLookupDefinitions();
	
}
