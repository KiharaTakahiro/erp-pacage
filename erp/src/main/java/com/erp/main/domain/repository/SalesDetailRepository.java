package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.SalesDetailEntity;

/**
 * 売上詳細リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetailEntity, Long> {
	
}
