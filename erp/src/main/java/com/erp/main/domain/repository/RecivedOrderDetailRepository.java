package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;

/**
 * 受注詳細リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface RecivedOrderDetailRepository extends JpaRepository<RecivedOrderDetailEntity, Long>, JpaSpecificationExecutor<RecivedOrderDetailEntity> {
	
	
}
