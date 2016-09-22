package com.dayswideawake.webrobot.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.aop.annotation.Loggable;
import com.dayswideawake.webrobot.backend.domain.LookupJob;
import com.dayswideawake.webrobot.backend.repository.dao.LookupJobRepository;
import com.dayswideawake.webrobot.backend.repository.entity.LookupJobEntity;
import com.dayswideawake.webrobot.backend.repository.entity.QLookupJobEntity;
import com.dayswideawake.webrobot.backend.service.transformer.LookupDefinitionTaskDomainEntityTransformer;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class LookupDefinitionTaskServiceImpl implements LookupDefinitionTaskService {

	private LookupJobRepository repository;
	private LookupDefinitionTaskDomainEntityTransformer transformer;
	
	@Autowired
	public LookupDefinitionTaskServiceImpl(LookupJobRepository repository, LookupDefinitionTaskDomainEntityTransformer transformer) {
		this.repository = repository;
		this.transformer = transformer;
	}

	@Override
	@Loggable
	public List<LookupJob> checkoutTasksForSchedule(Integer numberOfTasks) {
		QLookupJobEntity lookupDefinitionTaskEntity = QLookupJobEntity.lookupJobEntity;
        Long currentTimeStamp = new Date().getTime();
        BooleanExpression lastLookupWasMoreThanIntervalSecondsAgo = lookupDefinitionTaskEntity.lastLookupAt.add(lookupDefinitionTaskEntity.intervalInSeconds.multiply(1000)).loe(currentTimeStamp);
        BooleanExpression lookupHasNotBeenMadeYet = lookupDefinitionTaskEntity.lastLookupAt.isNull();
        BooleanExpression isNotQueuedNow = lookupDefinitionTaskEntity.isQueuedNow.isFalse();
        BooleanExpression needToSchedule = isNotQueuedNow.and(lookupHasNotBeenMadeYet.or(lastLookupWasMoreThanIntervalSecondsAgo));
        OrderSpecifier<Long> orderByLastLookupPlusIntervalAsc = lookupDefinitionTaskEntity.lastLookupAt.add(lookupDefinitionTaskEntity.intervalInSeconds.multiply(1000)).asc().nullsFirst();
        Iterable<LookupJobEntity> entities = repository.findAll(needToSchedule, orderByLastLookupPlusIntervalAsc);
        return StreamSupport
        		.stream(entities.spliterator(), false)
        		.map(entity -> transformer.entityToDomain(entity))
        		.collect(Collectors.toList());
	}

	@Override
	@Loggable
	public LookupJob addLookupDefinitionTask(LookupJob task) {
		LookupJobEntity entity = transformer.domainToEntity(task);
		entity = repository.save(entity);
		return transformer.entityToDomain(entity);
	}

	@Override
	@Loggable
	public void markTaskAsQueued(Long taskId, Boolean isQueued) {
		LookupJobEntity entity = repository.findOne(taskId);
		if(entity != null){
			entity.isQueuedNow(isQueued);
			repository.save(entity);
		}
	}

	@Override
	@Loggable
	public void markTaskLastLookupAt(Long jobId, Date lookupTime) {
		LookupJobEntity entity = repository.findOne(jobId);
		if(entity != null){
			entity.isQueuedNow(false);
			entity.setLastLookupAt(lookupTime.getTime());
			repository.save(entity);
		}
	}

}
