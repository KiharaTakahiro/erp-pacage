package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.PurchasingEntity;

/**
 * 仕入リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface PurchasingRepository extends JpaRepository<PurchasingEntity, Long> {
	
}
