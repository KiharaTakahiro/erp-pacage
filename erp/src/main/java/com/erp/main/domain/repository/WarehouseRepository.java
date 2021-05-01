package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.WarehouseEntity;

/**
 * 倉庫リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
	
}