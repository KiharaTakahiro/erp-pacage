package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.QuotationEntity;

/**
 * 見積リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface QuotationRepository extends JpaRepository<QuotationEntity, Long> {
	
}
