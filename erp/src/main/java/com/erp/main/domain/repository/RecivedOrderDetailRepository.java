package com.erp.main.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;

/**
 * 受注詳細リポジトリ
 * 
 * @author 木原
 *
 */
@Repository
public interface RecivedOrderDetailRepository extends JpaRepository<RecivedOrderDetailEntity, Long> {
	
	/*
	 * 受注SeqをもとにRecivedOrderDetialEntityを取得
	 * @return
	 */
	public Optional<RecivedOrderDetailEntity> findByRecivedOrderId(Long recivedOrderSeq);
}
