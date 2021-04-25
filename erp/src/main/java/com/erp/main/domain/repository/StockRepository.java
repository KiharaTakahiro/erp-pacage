package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.StockEntity;

/**
 * 在庫リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
	
}
