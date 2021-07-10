package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.ClientsEntity;

/**
 * 取引先リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long>, JpaSpecificationExecutor<ClientsEntity> {

	
}
