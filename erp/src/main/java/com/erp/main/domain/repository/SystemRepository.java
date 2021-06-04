package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.SystemEntity;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Long> {

}
