package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.CompanyEntity;

/**
 * 会社リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
	
}
