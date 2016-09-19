package com.dayswideawake.webrobot.backend.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.dayswideawake.webrobot.backend.repository.entity.LookupJobEntity;

@Repository
public interface LookupJobRepository extends JpaRepository<LookupJobEntity, Long>, QueryDslPredicateExecutor<LookupJobEntity> {

}
