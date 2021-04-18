package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.QuotationDetailEntity;

/**
 * 見積詳細リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface QuotationDetailRepository extends JpaRepository<QuotationDetailEntity, Long> {
	
}
