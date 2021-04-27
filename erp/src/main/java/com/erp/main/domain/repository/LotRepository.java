package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.LotEntity;

/**
 * ロットリポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface LotRepository extends JpaRepository<LotEntity, Long> {
	
}
