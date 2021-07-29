package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

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
import com.erp.main.domain.services.SupplierService;
/**
 * OrderServiceのパターン網羅用のテスト
 * @author ngt
 *
 */

<<<<<<< HEAD

=======
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
	
	@InjectMocks
	private SupplierService supplierService = new SupplierService();
	
	/**
	 * 発注リポジトリ
	 */
	@Mock
	private OrderRepository orderRepository; 

	/**
	 * 仕入先マスタのリポジトリ
	 */
	@Mock
	private SupplierRepository supplierRepository;

	/**
	 * 仕入先商品のリポジトリ
	 */
	@Mock
	private SupplierProductRepository supplierProductRepository;
	
	/**
	 * 金額コンポーネント
	 */
	@Mock
	private MoneyComponent moneyComponent;
	
	/**
	 * 正常系
	 * 通常の登録ケース
	 */
	@Test
	public void createOrderSucsessCase1() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createDefaultInputData();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = this.createDefaultSupplierProductData();		
		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		// 取得処理をモック化 (仕入商品)
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		this.supplierService.createOrder(createOrderVo);
		
		// 検証用データの作成
		OrderEntity entity = this.createVerifyDataByDefaltInput();
		// 消費税
		entity.setTax(70L);
		// 合計金額
		entity.setTotal(770L);
		// 値の検証
		Mockito.verify(this.orderRepository, times(1)).save(entity);
	}
	
	
	/**
	 * 異常系1
<<<<<<< HEAD
	 * 仕入先情報がないパターン
	 */
	@Test
	public void createOrderErrorCase1() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createDefaultInputData();
		Optional<SupplierEntity> supplier = this.createErrorSupplierData();
		Optional<SupplierProductEntity> supplierProduct = createDefaultSupplierProductData();
		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
=======
	 * 取引先情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase1() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createDefaultInputData();
		Optional<SupplierEntity> supplier = this.createErrorSupplierData();
		Optional<SupplierProductEntity> supplierProduct = this.createDefaultSupplierProductData();		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		// 取得処理をモック化 (仕入商品)
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> supplierService.createOrder(createOrderVo));
	}
	
	/**
	 * 異常系2
<<<<<<< HEAD
	 * 仕入先商品がないパターン
	 */
	@Test
	public void createOrderErrorCase2() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createDefaultInputData();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = createErrorSupplierProductData();
		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
=======
	 * 取引先商品情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase2() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createDefaultInputData();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = this.createErrorSupplierProductData();		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		// 取得処理をモック化 (仕入商品)
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> supplierService.createOrder(createOrderVo));
	}
	
	/**
	 * 異常系3
<<<<<<< HEAD
	 * 商品情報がないパターン
	 */
	@Test
	public void createOrderErrorCase3() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createErrorInputData();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = createDefaultSupplierProductData();
		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
=======
	 * 取引先商品情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase3() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createErrorInputData();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = this.createDefaultSupplierProductData();		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		// 取得処理をモック化 (仕入商品)
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> supplierService.createOrder(createOrderVo));
	}
	
	/**
	 * 異常系4
<<<<<<< HEAD
	 * 詳細情報がないパターン
	 */
	@Test
	public void createOrderErrorCase4() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createErrorInputData2();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = createDefaultSupplierProductData();
		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
=======
	 * 取引先商品情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase4() {
		// 実行用テストデータの作成
		CreateOrderVo createOrderVo = this.createErrorInputData2();
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Optional<SupplierProductEntity> supplierProduct = this.createDefaultSupplierProductData();		
		// 取得処理をモック化 (仕入先情報)
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		// 取得処理をモック化 (仕入商品)
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplierProduct);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> supplierService.createOrder(createOrderVo));
	}
	
	/**
	 * 仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createDefaultSupplierData() {
		SupplierEntity suppliers = new SupplierEntity();
		suppliers.setSupplierSeq(2L);
		return Optional.of(suppliers);
		}
	
	/**
	 * エラー用仕入れ先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createErrorSupplierData() {
		return Optional.empty();
		
	}


	/**
	 * 仕入先商品データ生成
	 * @return
	 */
	private Optional<SupplierProductEntity> createDefaultSupplierProductData(){
		SupplierProductEntity supplierProducts = new SupplierProductEntity();
<<<<<<< HEAD
		supplierProducts.setSupplierProductSeq(2L);
		supplierProducts.setPurchaseUnitPrice(10L);
=======
		supplierProducts.setPurchaseUnitPrice(1000L);		
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
		return Optional.of(supplierProducts);
	}
	
	/**
	 * エラー用仕入先商品データ生成
	 * @return
	 */
	private Optional<SupplierProductEntity> createErrorSupplierProductData(){
		return Optional.empty();
	}
	
	/**
	 * デフォルトの商品データ生成
	 * @return
	 */
	private CreateOrderVo createDefaultInputData() {
		CreateOrderVo createOrderVo = new CreateOrderVo();
		// 仕入先SEQ
		createOrderVo.setSupplierSeq(2L);
		// 発注金額
		createOrderVo.setTotal(2L);
		// 消費税
		createOrderVo.setTax(2L);
		
		CreateOrderDetailVo createOrderDetailVo = new CreateOrderDetailVo();
		// 仕入商品Seq
		createOrderDetailVo.setSupplierProductSeq(2L);
		// 発注ステータス
		createOrderDetailVo.setStatus(null);
		// 数量
		createOrderDetailVo.setQuantity(2);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		details.add(createOrderDetailVo);
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
	
	/**
	 * エラー用の商品データ生成
	 * @return
	 */
	private CreateOrderVo createErrorInputData() {
		CreateOrderVo createOrderVo = new CreateOrderVo();
		// 仕入先SEQ
		createOrderVo.setSupplierSeq(2L);
		// 発注金額
		createOrderVo.setTotal(2L);
		// 消費税
		createOrderVo.setTax(2L);
		
		CreateOrderDetailVo createOrderDetailVo = new CreateOrderDetailVo();
		// 仕入商品Seq
		createOrderDetailVo.setSupplierProductSeq(2L);
		// 発注ステータス
		createOrderDetailVo.setStatus(null);
		// 数量
		createOrderDetailVo.setQuantity(0);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		details.add(createOrderDetailVo);
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
	
	/**
	 * エラー用の商品データ生成
	 * @return
	 */
	private CreateOrderVo createErrorInputData2() {
		CreateOrderVo createOrderVo = new CreateOrderVo();
		// 仕入先SEQ
		createOrderVo.setSupplierSeq(2L);
		// 発注金額
		createOrderVo.setTotal(2L);
		// 消費税
		createOrderVo.setTax(2L);
		
		CreateOrderDetailVo createOrderDetailVo = new CreateOrderDetailVo();
		// 仕入商品Seq
		createOrderDetailVo.setSupplierProductSeq(2L);
		// 発注ステータス
		createOrderDetailVo.setStatus(null);
		// 数量
		createOrderDetailVo.setQuantity(2);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
	/**
	 * デフォルトのテストデータの場合の検証データ
	 * @return
	 */
	
	private OrderEntity createVerifyDataByDefaltInput() {
		OrderEntity entity = new OrderEntity();
		// 仕入先SEQ
		entity.setSupplierSeq(2L);
		// 発注金額
		entity.setTotal(2L);
		// 消費税
		entity.setTax(2L);
		
		OrderDetailEntity detailEntity = new OrderDetailEntity();
		// 仕入商品Seq
		detailEntity.setSupplierProductSeq(2L);
		detailEntity.setStatus(null);
		detailEntity.setQuantity(4);
		detailEntity.setDeriveryDate("20210503");
		return entity;
	}
<<<<<<< HEAD

	
	/**
	 * エラー用の仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createErrorSupplierData(){
		// 仕入先を空に
		return Optional.empty();
	}

	/**
	 * エラー用の仕入商品データ生成
	 * @return
	 */
	private Optional<SupplierProductEntity> createErrorSupplierProductData(){
		// 仕入先を空に
		return Optional.empty();
	}

	/**
	 * 数量のエラー用の商品データ生成
	 * @return
	 */
	private CreateOrderVo createErrorInputData() {
		CreateOrderVo createOrderVo = new CreateOrderVo();
		// 仕入先SEQ
		createOrderVo.setSupplierSeq(2L);
		// 発注金額
		createOrderVo.setTotal(2L);
		// 消費税
		createOrderVo.setTax(2L);
		
		CreateOrderDetailVo createOrderDetailVo = new CreateOrderDetailVo();
		// 仕入商品Seq
		createOrderDetailVo.setSupplierProductSeq(2L);
		// 発注ステータス
		createOrderDetailVo.setStatus(null);
		// 数量
		createOrderDetailVo.setQuantity(0);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		details.add(createOrderDetailVo);
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
	
	
	/**
	 * 詳細情報のエラー用の商品データ生成
	 * @return
	 */
	private CreateOrderVo createErrorInputData2() {
		CreateOrderVo createOrderVo = new CreateOrderVo();
		// 仕入先SEQ
		createOrderVo.setSupplierSeq(2L);
		// 発注金額
		createOrderVo.setTotal(2L);
		// 消費税
		createOrderVo.setTax(2L);

		CreateOrderDetailVo createOrderDetailVo = new CreateOrderDetailVo();
		// 仕入商品Seq
		createOrderDetailVo.setSupplierProductSeq(2L);
		// 発注ステータス
		createOrderDetailVo.setStatus(null);
		// 数量
		createOrderDetailVo.setQuantity(0);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
=======
	
>>>>>>> ea41e30272f1df9a3915b2c3562e4b2fdce443a6
	
}