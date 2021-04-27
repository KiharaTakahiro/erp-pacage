package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.DestinationEntity;

/**
 * 出荷先リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
	
}
