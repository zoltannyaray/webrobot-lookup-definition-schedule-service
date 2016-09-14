package com.dayswideawake.webrobot.backend.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.dayswideawake.webrobot.backend.repository.entity.LookupDefinitionTaskEntity;

@Repository
public interface LookupDefinitionTaskRepository extends JpaRepository<LookupDefinitionTaskEntity, Long>, QueryDslPredicateExecutor<LookupDefinitionTaskEntity> {

}
