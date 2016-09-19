package com.dayswideawake.webrobot.backend.service;

import java.util.Date;
import java.util.List;

import com.dayswideawake.webrobot.backend.domain.LookupJob;

public interface LookupDefinitionTaskService {

	List<LookupJob> checkoutTasksForSchedule(Integer numberOfTasks);
	
	LookupJob addLookupDefinitionTask(LookupJob task);
	
	void markTaskAsQueued(Long taskId, Boolean isQueued);
	
	void markTaskLastLookupAt(Long taskId, Date lookupTime);
}
