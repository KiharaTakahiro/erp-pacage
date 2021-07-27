package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.OrderEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.repository.OrderRepository;
import com.erp.main.domain.repository.SupplierRepository;

/*
 * 受注のサービス
 * @author takayama
 */

public class SupplierService {
	/*
	 * 発注レポジトリ
	 */
	@Autowired
	private OrderRepository orderRepository; 

	/**
	 * 仕入先マスタのリポジトリ
	 */
	@Autowired
	private SupplierRepository supplierRepository;
	

	/**
	 * 発注作成処理
	 * @param createOrderVo
	 */
	@Transactional
	public void  createOrder(CreateOrderVo createOrderVo) {
		
		//取引先の有無の確認		
		Optional<SupplierEntity> supplier = this.supplierRepository.findById(createOrderVo.getSupplierSeq());
		if(supplier.isEmpty()) {
			throw new AppException(String.format("対象の取引先が取得できません。companySeq: %s",createOrderVo.getSupplierSeq()));
		}
		// 発注詳細の作成
		Set<OrderEntity> detailEntities = new HashSet<>();
		// 発注金額
		var totalPrice = 0L;
		// 消費税
		var tax = 0L;
		
			
		
	}
	

}
