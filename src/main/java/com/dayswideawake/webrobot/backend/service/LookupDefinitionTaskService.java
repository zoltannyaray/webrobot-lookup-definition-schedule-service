package com.dayswideawake.webrobot.backend.service;

import java.util.List;

import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;

public interface LookupDefinitionTaskService {

	List<LookupDefinitionTask> checkoutTasksForSchedule(Integer numberOfTasks);
	
}
