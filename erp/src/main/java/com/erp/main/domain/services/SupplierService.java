package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.OrderEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;
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
		
		// 詳細の入力確認
		if(createOrderVo.getDetails().isEmpty()) {
			throw new AppException("受注詳細が入力されていません");
		}
		
		for(CreateOrderDetailVo detailVo: createOrderVo.getDetails()) {
			// 商品の取得
			Optional<ProductEntity> product = this.productRepository.findById(detailVo.getProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getProductSeq()));
			}
			
			
			// 数量がマイナスの場合はエラー
			if(detailVo.getQuantity() < 0) {
				throw new AppException(String.format("数量は正の整数で入力してください。quantity: %s",detailVo.getQuantity()));
			}
			
			
			// 合計金額を加算する
			totalPrice += product.get().getUnitPrice() * detailVo.getQuantity();	
		
	}
	

}
