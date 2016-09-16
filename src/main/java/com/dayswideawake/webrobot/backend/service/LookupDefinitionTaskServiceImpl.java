package com.dayswideawake.webrobot.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayswideawake.backend.repository.entity.QLookupDefinitionTaskEntity;
import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.domain.LookupDefinitionTask;
import com.dayswideawake.webrobot.backend.repository.dao.LookupDefinitionTaskRepository;
import com.dayswideawake.webrobot.backend.repository.entity.LookupDefinitionTaskEntity;
import com.dayswideawake.webrobot.backend.service.transformer.LookupDefinitionTaskDomainEntityTransformer;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class LookupDefinitionTaskServiceImpl implements LookupDefinitionTaskService {

	private LookupDefinitionTaskRepository repository;
	private LookupDefinitionTaskDomainEntityTransformer transformer;
	
	@Autowired
	public LookupDefinitionTaskServiceImpl(LookupDefinitionTaskRepository repository, LookupDefinitionTaskDomainEntityTransformer transformer) {
		this.repository = repository;
		this.transformer = transformer;
	}

	@Override
	@Loggable
	public List<LookupDefinitionTask> checkoutTasksForSchedule(Integer numberOfTasks) {
		QLookupDefinitionTaskEntity lookupDefinitionTaskEntity = QLookupDefinitionTaskEntity.lookupDefinitionTaskEntity;
        Long currentTimeStamp = new Date().getTime();
        BooleanExpression lastLookupWasMoreThanIntervalSecondsAgo = lookupDefinitionTaskEntity.lastLookupAt.add(lookupDefinitionTaskEntity.intervalInSeconds.multiply(1000)).loe(currentTimeStamp);
        BooleanExpression lookupHasNotBeenMadeYet = lookupDefinitionTaskEntity.lastLookupAt.isNull();
        BooleanExpression isNotQueuedNow = lookupDefinitionTaskEntity.isQueuedNow.isFalse();
        BooleanExpression needToSchedule = isNotQueuedNow.and(lookupHasNotBeenMadeYet.or(lastLookupWasMoreThanIntervalSecondsAgo));
        Iterable<LookupDefinitionTaskEntity> entities = repository.findAll(needToSchedule);
        return StreamSupport
        		.stream(entities.spliterator(), false)
        		.map(entity -> transformer.entityToDomain(entity))
        		.collect(Collectors.toList());
	}

	@Override
	@Loggable
	public LookupDefinitionTask addLookupDefinitionTask(LookupDefinitionTask task) {
		LookupDefinitionTaskEntity entity = transformer.domainToEntity(task);
		entity = repository.save(entity);
		return transformer.entityToDomain(entity);
	}

	@Override
	@Loggable
	public void markTaskAsQueued(Long taskId, Boolean isQueued) {
		LookupDefinitionTaskEntity entity = repository.findOne(taskId);
		if(entity != null){
			entity.isQueuedNow(isQueued);
		}
		repository.save(entity);
	}

}
