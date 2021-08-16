package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.SupplierProductRelationEntity;

/**
 * 仕入先商品リポジトリ
 * 
 * @author 高山
 *
 */
@Repository
public interface SupplierProductsRelationRepository extends JpaRepository<SupplierProductRelationEntity, Long> , JpaSpecificationExecutor<SupplierProductRelationEntity> {

}