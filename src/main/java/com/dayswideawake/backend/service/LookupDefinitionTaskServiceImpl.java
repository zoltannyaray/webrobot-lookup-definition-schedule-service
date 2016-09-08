package com.dayswideawake.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.dayswideawake.backend.domain.LookupDefinitionTask;
import com.dayswideawake.backend.repository.dao.LookupDefinitionTaskRepository;
import com.dayswideawake.backend.repository.entity.LookupDefinitionTaskEntity;
import com.dayswideawake.backend.repository.entity.QLookupDefinitionTaskEntity;
import com.dayswideawake.backend.service.transformer.LookupDefinitionTaskDomainEntityTransformer;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class LookupDefinitionTaskServiceImpl implements LookupDefinitionTaskService {

	private LookupDefinitionTaskRepository repository;
	private LookupDefinitionTaskDomainEntityTransformer transformer;
	
	@Override
	public List<LookupDefinitionTask> checkoutTasksForSchedule(Integer numberOfTasks) {
		QLookupDefinitionTaskEntity lookupDefinitionTaskEntity = QLookupDefinitionTaskEntity.lookupDefinitionTaskEntity;
        Long currentTimeStamp = new Date().getTime();
        BooleanExpression lastLookupWasMoreThanIntervalSecondsAgo = lookupDefinitionTaskEntity.lastLookupAt.add(lookupDefinitionTaskEntity.intervalInSeconds.multiply(1000)).loe(currentTimeStamp);
        BooleanExpression lookupHasNotBeenMadeYet = lookupDefinitionTaskEntity.lastLookupAt.isNull();
        BooleanExpression needToSchedule = lookupHasNotBeenMadeYet.or(lastLookupWasMoreThanIntervalSecondsAgo);
        Iterable<LookupDefinitionTaskEntity> entities = repository.findAll(needToSchedule);
        return StreamSupport
        		.stream(entities.spliterator(), false)
        		.map(entity -> transformer.entityToDomain(entity))
        		.collect(Collectors.toList());
	}

}
