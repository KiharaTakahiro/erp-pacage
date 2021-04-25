package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.OrderEntity;

/**
 * 発注リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
}
