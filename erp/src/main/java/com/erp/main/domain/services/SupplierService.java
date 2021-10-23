package com.erp.main.domain.services;

import java.util.ArrayList;
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
import com.erp.main.domain.objects.entity.SupplierProductRelationEntity;
import com.erp.main.domain.objects.model.SupplierModel;
import com.erp.main.domain.objects.model.SupplierProductModel;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierProductConditionVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierProductsVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierVo;
import com.erp.main.domain.objects.valueobjects.GetSuppliersVo;
import com.erp.main.domain.objects.valueobjects.SupplierProductRelationVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierVo;
import com.erp.main.domain.repository.OrderRepository;
import com.erp.main.domain.repository.SupplierProductRelationRepository;
import com.erp.main.domain.repository.SupplierProductRepository;
import com.erp.main.domain.repository.SupplierRepository;
import com.erp.main.domain.specification.SupplierProductSpec;
import com.erp.main.domain.specification.SupplierSpec;

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
	 * 仕入商品マスタのリポジトリ
	 */
	@Autowired
	private SupplierProductRepository supplierProductRepository;
	
	/**
	 * 仕入商品関連マスタのリポジトリ
	 */
	@Autowired
	private SupplierProductRelationRepository supplierProductRelationRepository;
	
	/**
	 * 金額コンポーネント
	 */
	@Autowired
	private MoneyComponent moneyComponent;
	

	/**
	 * 仕入先マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createSupplier(CreateSupplierVo vo) {
		SupplierEntity entity = SupplierEntity.create(vo);
		this.supplierRepository.save(entity);
	}
	

	/**
	 * 仕入先詳細画面のレスポンス
	 * @param vo
	 */
	@Transactional
	public GetSupplierVo getSupplierVo(Long supplierSeq){
		Optional<SupplierEntity> supplier = supplierRepository.findById(supplierSeq);
		if(supplier.isEmpty()) {
			throw new AppException(String.format("該当の仕入先を取得できませんでした。 supplierSeq: %s", supplierSeq));
		}
		
		return GetSupplierVo.mapTo(supplier.get());
	
	}
	
	
	/**
	 * 仕入先一覧取得
	 * @param vo
	 */
	@Transactional
	public GetSuppliersVo getSupplierVo(GetSupplierConditionsVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<SupplierEntity> spec = Specification.where(
				SupplierSpec.supplierSeqEquals(condition.getSupplierSeq()))
				.and(SupplierSpec.supplierNameEquals(condition.getSupplierName()));
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "supplierSeq");
		
		Page<SupplierEntity> pages = this.supplierRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		List<SupplierModel> suppliers = pages.get().map(e -> {
			var supplier = new SupplierModel();
			// 仕入先seq
			supplier.setSupplierSeq(e.getSupplierSeq());
			// 仕入先名
			supplier.setSupplierName(e.getName());
			return supplier;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetSuppliersVo();
		// トータルぺ―ジ
		vo.setTotalItemsNum(pages.getTotalElements());
		// 取引先リストの設定
		vo.setSupplier(suppliers);
		
		return vo;
	}
	
	/*
	 * 仕入先一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetSuppliersVo pullDownSupplier() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "supplierSeq");
		
		// 取引先一覧取得
		List<SupplierEntity> entitys = this.supplierRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<SupplierModel> suppliers =  new ArrayList<>();
		
		for(SupplierEntity entity: entitys) {		
			var supplier = new SupplierModel();
			// 仕入先seq
			supplier.setSupplierSeq(entity.getSupplierSeq());
			// 仕入先名
			supplier.setSupplierName(entity.getName());
			
			suppliers.add(supplier);
			
		}
	
		var vo = new GetSuppliersVo();
		// 取引先リストの設定
		vo.setSupplier(suppliers);
		
		return vo;
	}
	
	/**
	 * 仕入先更新処理
	 * @param vo
	 */
	@Transactional
	public void updateSupplier(UpdateSupplierVo vo) {
		// 仕入先を取得
		var supplier= this.supplierRepository.findById(vo.getSupplier().getSupplierSeq());
		
		// 対象の仕入先が取得できない場合はエラー
		if(supplier.isEmpty()) {
			throw new AppException(String.format("該当の仕入先を取得できませんでした。 supplierSeq: %s", vo.getSupplier().getSupplierSeq()));			
		}
		
		var supplierEntity = supplier.get();
		supplierEntity.update(vo);
		this.supplierRepository.save(supplierEntity);
	}
	
	/**
	 * 仕入商品マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createSupplierProduct(CreateSupplierProductVo vo) {
		Optional<SupplierEntity> product = this.supplierRepository.findById(vo.getSupplierSeq());
		if(product.isEmpty()) {
			throw new AppException(String.format("対象の仕入先が取得できません。supplierySeq: %s",vo.getSupplierSeq()));
		}
		SupplierProductEntity entity = SupplierProductEntity.create(vo);
		var supplierProductEntity = this.supplierProductRepository.saveAndFlush(entity);
		
		
		var relationVo = new SupplierProductRelationVo();
		relationVo.setSupplierSeq(vo.getSupplierSeq());
		relationVo.setSupplierProductSeq(supplierProductEntity.getSupplierProductSeq());

		SupplierProductRelationEntity relationEntity = SupplierProductRelationEntity.create(relationVo);
		this.supplierProductRelationRepository.save(relationEntity);
	}
	
	
	/*
	 * 仕入商品一覧取得
	 * @param condition
	 * @return
	 */
	
	public GetSupplierProductsVo getSupplierProductsVo(GetSupplierProductConditionVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<SupplierProductEntity> spec = Specification.where(
					 SupplierProductSpec.supplierProductSeqEquals(condition.getSupplierProductSeq()))
				.and(SupplierProductSpec.supplierProductNameEquals(condition.getSupplierProductName())
				.and(SupplierProductSpec.purchaseUnitPriceFrom(condition.getPurchaseUnitPriceFrom()))
				.and(SupplierProductSpec.purchaseUnitPriceTo(condition.getPurchaseUnitPriceTo())));
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "supplierProductSeq");
	
		Page<SupplierProductEntity> pages = this.supplierProductRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		List<SupplierProductModel> supplierProducts = pages.get().map(e -> {
			var supplierProduct = new SupplierProductModel();
			// 仕入商品seq
			supplierProduct.setSupplierProductSeq(e.getSupplierProductSeq());
			// 仕入商品名
			supplierProduct.setSupplierProductName(e.getName());
			// 仕入価格
			supplierProduct.setPurchaseUnitPrice(e.getPurchaseUnitPrice());
			return supplierProduct;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetSupplierProductsVo();
		// トータルぺ―ジ
		vo.setTotalItemsNum(pages.getTotalElements());
		// 仕入先リストの設定
		vo.setSupplierProduct(supplierProducts);
		
		return vo;

	}
	

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
			Optional<SupplierProductEntity> productOpt = this.supplierProductRepository.findById(detailVo.getSupplierProductSeq());
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

}
