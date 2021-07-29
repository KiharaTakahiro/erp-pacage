package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;
import com.erp.main.domain.repository.OrderRepository;
import com.erp.main.domain.repository.SupplierProductRepository;
import com.erp.main.domain.services.SupplierService;

import antlr.collections.List;

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
		
		// 取得処理をモック化
		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplier);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		// 処理の実行
		this.supplierService.createOrder(createOrderVo);
		
		// 検証用データの作成
		SupplierService entity = this.createVerifyDataByDefaltInput();
		// 消費税
		entity.setTax(70L);
		// 合計金額
		entity.setTotal(770L);
		// 値の検証
		Mockito.verify(this.orderRepository, times(1)).save(entity);
	}
	
	
	
	/**
	 * 異常系1
	 * 取引先情報がないパターン
	 */
//	@Test
//	public void createOrderErrorCase1() {
//		// 実行用テストデータの作成
//		CreateOrderVo createOrderVo = this.createDefaultInputData();
//		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
//		
//		// 取得処理をモック化
//		Mockito.when(this.supplierProductRepository.findById(2L)).thenReturn(supplier);
//		// 消費税はサービスのテストでは10%として考える
//		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
//		// 処理の実行
//		Assertions.assertThrows(AppException.class, () -> orderService.createSupplier(createSupplier));
//		}
	
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
		createOrderDetailVo.setQuantity(4);
		// 納期
		createOrderDetailVo.setDeriveryDate("20210503");
		
		List<CreateOrderDetailVo> details = new ArrayList<>();
		details.add(createOrderVo);
		createOrderVo.setDetails(details);
		
		return createOrderVo;
	}
	
	
	/**
	 * デフォルトのテストデータの場合の検証データ
	 * @return
	 */
	
	private SupplierEntity createVerifyDataByDefaltInput() {
		SupplierEntity entity = new SupplierEntity();
		// 仕入先SEQ
		entity.setSupplierSeq(2L);
		// 発注金額
		entity.setTotal(2L);
		// 消費税
		entity.setTax(2L);
		
	}
	
	
	
	
	
	
	
	
	
	
}