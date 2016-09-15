package com.dayswideawake.webrobot.messaging.producer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.Channels;
import com.dayswideawake.webrobot.messaging.model.LookupJobMessage;
import com.dayswideawake.webrobot.messaging.transformer.LookupDefinitionTaskDomainMessageTransformer;

@Component
public class LookupJobEmitter {

	private Channels channels;
	private Integer numberOfTasksToEmitAtOnce = 10;
	private LookupDefinitionTaskService lookupDefinitionTaskService;
	private LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer;
	private static final Logger LOGGER = Logger.getLogger(LookupJobEmitter.class.getName());

	@Autowired
	public LookupJobEmitter(Channels channels, LookupDefinitionTaskService lookupDefinitionTaskService, LookupDefinitionTaskDomainMessageTransformer domainMessageTransformer) {
		this.channels = channels;
		this.lookupDefinitionTaskService = lookupDefinitionTaskService;
		this.domainMessageTransformer = domainMessageTransformer;
	}

	@Scheduled(fixedRate = 5000)
	@Loggable
	public void emitLookupJobs() {
		LOGGER.log(Level.INFO, "emit");
		List<LookupDefinitionTask> tasks = lookupDefinitionTaskService.checkoutTasksForSchedule(numberOfTasksToEmitAtOnce);
		tasks.stream().forEach(domainTask -> emitLookupJob(domainTask));
	}

	@Loggable
	private void emitLookupJob(LookupDefinitionTask task) {
		LookupJobMessage payload = domainMessageTransformer.domainTaskToJobMessage(task);
		Message<LookupJobMessage> message = MessageBuilder.withPayload(payload).build();
		channels.lookupTasks().send(message);
		lookupDefinitionTaskService.markTaskAsQueued(task.getId().get(), true);
	}

}
