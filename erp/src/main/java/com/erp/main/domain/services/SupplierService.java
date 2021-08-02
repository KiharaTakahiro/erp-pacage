package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.OrderDetailEntity;
import com.erp.main.domain.objects.entity.OrderEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.entity.SupplierProductEntity;
import com.erp.main.domain.objects.model.SupplierProductModel;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierProductsConditionVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierProductsVo;
import com.erp.main.domain.repository.OrderRepository;
import com.erp.main.domain.repository.SupplierProductsRepository;
import com.erp.main.domain.repository.SupplierRepository;
import com.erp.main.domain.specification.SupplierProductsSpec;

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
	private SupplierProductsRepository supplierProductsRepository;

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
			Optional<SupplierProductEntity> productOpt = this.supplierProductsRepository.findById(detailVo.getSupplierProductSeq());
			if(productOpt.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getSupplierProductSeq()));
			}
			
			// 数量がマイナスの場合はエラー
			if(detailVo.getQuantity() <= 0) {
				throw new AppException(String.format("数量は正の整数で入力してください。quantity: %s",detailVo.getQuantity()));
			}
			
			var product = productOpt.get();
			// 合計金額を加算する
			totalPrice += product.getPurchaseUnitPrice() * detailVo.getQuantity();	
			// 金額 (単金 × 数量)
			long price = product.getPurchaseUnitPrice() * detailVo.getQuantity();
			//商品ごとの税金タイプ
			var taxTaype = product.getTaxType();
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
		this.orderRepository.save(order);
	}
	
	/*
	 * 仕入先一覧取得
	 * @param condition
	 * @return
	 */
	
	public GetSupplierProductsVo getSupplierProducstVo(GetSupplierProductsConditionVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<SupplierProductEntity> spec = Specification.where(
				SupplierProductsSpec.supplierProductsSeqEquals(condition.getSupplierProductsSeq()))
				.and(SupplierProductsSpec.supplierProductsNameEquals(condition.getSupplierProductsName()));
		
		// ソートの設定
	var sort = Sort.by(Sort.Direction.ASC, "supplierProductSeq");

	Page<SupplierProductEntity> pages = this.supplierProductsRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
	List<SupplierProductModel> supplierProducts = pages.get().map(e -> {
		var supplierProduct = new SupplierProductModel();
		// 仕入先seq
		supplierProduct.setSupplierProductSeq(e.getSupplierProductSeq());
		// 仕入先名
		supplierProduct.setSupplierProductName(e.getName());
		return supplierProduct;
	}).collect(Collectors.toList());
	
	// 返却用のVo生成
	var vo = new GetSupplierProductsVo();
	// トータルぺ―ジ
	vo.setMaxpage(pages.getTotalPages());
	// 仕入先リストの設定
	vo.setSupplierProducts(supplierProducts);
	
	return vo;

	}
}
