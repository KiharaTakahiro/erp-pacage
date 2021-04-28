package com.erp.main.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.ProductEntity;

/**
 * 商品リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
