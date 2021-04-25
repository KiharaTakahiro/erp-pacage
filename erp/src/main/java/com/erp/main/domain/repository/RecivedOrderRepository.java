package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.RecivedOrderEntity;

/**
 * 受注リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface RecivedOrderRepository extends JpaRepository<RecivedOrderEntity, Long> {
	
}
