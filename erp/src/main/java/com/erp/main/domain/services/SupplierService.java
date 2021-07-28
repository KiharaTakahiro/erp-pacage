package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.OrderDetailEntity;
import com.erp.main.domain.objects.entity.OrderEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.entity.SupplierProductEntity;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;
import com.erp.main.domain.repository.OrderRepository;
import com.erp.main.domain.repository.SupplierProductRepository;
import com.erp.main.domain.repository.SupplierRepository;

/*
 * 受注のサービス
 * @author takayama
 */

@Service
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
	 * 仕入先商品のリポジトリ
	 */
	@Autowired
	private SupplierProductRepository supplierProductRepository;

	/**
	 * 金額コンポーネント
	 */
	@Autowired
	private MoneyComponent moneyComponent;
	
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
		Set<OrderDetailEntity> detailEntities = new HashSet<>();
		// 発注金額
		var totalPrice = 0L;
		// 消費税
		var taxTotal = 0L;
		
		// 詳細の入力確認
		if(createOrderVo.getDetails().isEmpty()) {
			throw new AppException("受注詳細が入力されていません");
		}
		
		for(CreateOrderDetailVo detailVo: createOrderVo.getDetails()) {
			
			// 商品の取得
			Optional<SupplierProductEntity> product = this.supplierProductRepository.findById(detailVo.getSupplierProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getSupplierProductSeq()));
			}
			// 数量がマイナスの場合はエラー
			if(detailVo.getQuantity() <= 0) {
				throw new AppException(String.format("数量は正の整数で入力してください。quantity: %s",detailVo.getQuantity()));
			}
			
			
			// 合計金額を加算する
			totalPrice += product.get().getPurchaseUnitPrice() * detailVo.getQuantity();	
			
			// 金額 (単金 × 数量)
			long price = product.get().getPurchaseUnitPrice() * detailVo.getQuantity();
			
			//商品ごとの税金タイプ
			var taxTaype = product.get().getTaxType();
			
			//税金の合計を加算
			taxTotal += this.moneyComponent.computeTax(price, taxTaype);
			
			
			// 受注詳細用のエンティティ生成
			OrderDetailEntity detailEntity = OrderDetailEntity.create(detailVo);

			// 見積詳細の追加
			detailEntities.add(detailEntity);
			
		}
			
		OrderEntity order = OrderEntity.create(createOrderVo);
		// 合計金額
		order.setTotal(totalPrice + taxTotal);
		
		// 発注詳細をセット
		order.setOrderDetailEntity(detailEntities);
		
		// 発注の保存
		order = this.orderRepository.save(order);

	}
}
