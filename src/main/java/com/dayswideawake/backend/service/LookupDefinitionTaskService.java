package com.dayswideawake.backend.service;

import java.util.List;

import com.dayswideawake.backend.domain.LookupDefinitionTask;

public interface LookupDefinitionTaskService {

	List<LookupDefinitionTask> checkoutTasksForSchedule(Integer numberOfTasks);
	
}
