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

import com.erp.main.domain.common.enums.BaseEnum;
import com.erp.main.domain.common.enums.RacivedOrderStatus;
import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.entity.RecivedOrderDetailEntity;
import com.erp.main.domain.objects.entity.RecivedOrderEntity;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.QuotationRepository;
import com.erp.main.domain.repository.RecivedOrderRepository;
import com.erp.main.domain.services.RecivedOrderService;

/**
 * RecivedOrderServiceのパターン網羅用のテスト
 * @author ngt
 *
 */

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class RecivedOrderServiceTest {
	
	@InjectMocks
	private RecivedOrderService recivedOrderService = new RecivedOrderService();
	
	/**
	 * 受注リポジトリ
	 */
	@Mock
	private RecivedOrderRepository recivedOrderRepository;
	
	/**
	 * 商品リポジトリ
	 */
	@Mock
	private ProductRepository productRepository;

	/**
	 * 見積リポジトリ
	 */
	@Mock
	private QuotationRepository quotationRepository;
	
	/**
	 * 取引先リポジトリ
	 */
	@Mock
	private ClientsRepository clientsRepository;
	
	/**
	 * 会社リポジトリ
	 */
	@Mock
	private CompanyRepository companyRepository;
	
	/**
	 * 部署リポジトリ
	 */
	@Mock
	private DepartmentRepository departmentRepository;
	
	/**
	 * 金額コンポーネント
	 * 
	 */
	@Mock
	private MoneyComponent moneyComponent;
	
	/*
	 * 通常のケース
	 */
	@Test
	public void createRecivedOrderSuccessCase1() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		this.recivedOrderService.createRecivedOrder(createRecivedOrderVo);
		
		// 検証用データの作成
		RecivedOrderEntity entity = this.createVerifyDataByDefaltInput();
		// 消費税
		entity.setTax(70L);
		// 合計金額
		entity.setTotal(770L);
		// 値の検証
		Mockito.verify(this.recivedOrderRepository, times(1)).save(entity);

		
	}
	
	/*
	 * 異常のケース
	 * 取引先無し
	 */
	@Test
	public void createRecivedOrderErrorCase1() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createErrorClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));

		
	}
	
	/*
	 * 異常のケース
	 * 会社無し
	 */
	@Test
	public void createRecivedOrderErrorCase2() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createErrorCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));

		
	}
	
	/*
	 * 異常のケース
	 * 部署無し
	 */
	@Test
	public void createRecivedOrderErrorCase3() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createErrorDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));

		
	}
	
	/*
	 * 異常のケース
	 * 見積無し
	 */
	@Test
	public void createRecivedOrderErrorCase4() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createErrorQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));

		
	}
	
	/*
	 * 異常のケース
	 * 商品無し
	 */
	@Test
	public void createRecivedOrderErrorCase5() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createErrorProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));

		
	}
	
	/*
	 * 異常のケース
	 * 値引がマイナス
	 */
	@Test
	public void createRecivedOrderErrorCase6() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createErrorInputData1();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);

		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));
		
	}
	
	
	/*
	 * 異常のケース
	 * 個数がマイナス
	 */
	@Test
	public void createRecivedOrderErrorCase7() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createErrorInputData2();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);

		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));
		
	}
	
	/*
	 * 異常のケース
	 * 詳細が無い
	 */
	@Test
	void createRecivedOrderErrorCase8() {
		// 実行用のテストデータ
		CreateRecivedOrderVo createRecivedOrderVo = this.createErrorInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Optional<QuotationEntity> quotationOpt = this.createDefaultQuotationData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 取得処理をモック化(見積情報)
		Mockito.when(this.quotationRepository.findById(39L)).thenReturn(quotationOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L, null)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> recivedOrderService.createRecivedOrder(createRecivedOrderVo));
				

		
	}
	
	/**
	 * デフォルトの商品データ生成
	 * @return
	 */
	private CreateRecivedOrderVo createDefaultInputData() {
		CreateRecivedOrderVo createRecivedOrderVo = new CreateRecivedOrderVo();
		
		// 取引先SEQ
		createRecivedOrderVo.setClientsSeq(2L);
		// 部門SEQ
		createRecivedOrderVo.setDepartmentSeq(2L);
		// 会社SEQ
		createRecivedOrderVo.setCompanySeq(2L);
		// 見積SEQ
		createRecivedOrderVo.setQuotationSeq(39L);
		// 受注日
		createRecivedOrderVo.setRecivedOrderDate("20210503");
		
		
		CreateRecivedOrderDetailVo createRecivedOrderDetailVo = new CreateRecivedOrderDetailVo();
		// 配送日
		createRecivedOrderDetailVo.setDeriveryDate("20210503");
		// 値引
		createRecivedOrderDetailVo.setDiscount(100L);
		// ロットSEQ
		createRecivedOrderDetailVo.setLotSeq(2L);
		// 商品SEQ
		createRecivedOrderDetailVo.setProductSeq(2L);
		// 数量
		createRecivedOrderDetailVo.setQuantity(4);
		// 商品SEQ
		createRecivedOrderDetailVo.setStatus(0);
		
		List<CreateRecivedOrderDetailVo> details = new ArrayList<>();
		details.add(createRecivedOrderDetailVo);
		createRecivedOrderVo.setDetails(details);
		return createRecivedOrderVo;
	}
	
	/**
	 * エラー用（値引がマイナス）の商品データ生成
	 * @return
	 */
	private CreateRecivedOrderVo createErrorInputData1() {
		CreateRecivedOrderVo createRecivedOrderVo = new CreateRecivedOrderVo();
		
		// 取引先SEQ
		createRecivedOrderVo.setClientsSeq(2L);
		// 部門SEQ
		createRecivedOrderVo.setDepartmentSeq(2L);
		// 会社SEQ
		createRecivedOrderVo.setCompanySeq(2L);
		// 見積SEQ
		createRecivedOrderVo.setQuotationSeq(39L);
		// 受注日
		createRecivedOrderVo.setRecivedOrderDate("20210503");
		
		
		CreateRecivedOrderDetailVo createRecivedOrderDetailVo = new CreateRecivedOrderDetailVo();
		// 配送日
		createRecivedOrderDetailVo.setDeriveryDate("20210503");
		// 値引
		createRecivedOrderDetailVo.setDiscount(-100L);
		// ロットSEQ
		createRecivedOrderDetailVo.setLotSeq(2L);
		// 商品SEQ
		createRecivedOrderDetailVo.setProductSeq(2L);
		// 数量
		createRecivedOrderDetailVo.setQuantity(4);
		// 商品SEQ
		createRecivedOrderDetailVo.setStatus(0);
		
		List<CreateRecivedOrderDetailVo> details = new ArrayList<>();
		details.add(createRecivedOrderDetailVo);
		createRecivedOrderVo.setDetails(details);
		return createRecivedOrderVo;
	}
	
	/**
	 * エラー用の商品データ生成
	 * 詳細がない
	 * @return
	 */
	private CreateRecivedOrderVo createErrorInputData() {
		CreateRecivedOrderVo createRecivedOrderVo = new CreateRecivedOrderVo();
		
		// 取引先SEQ
		createRecivedOrderVo.setClientsSeq(2L);
		// 部門SEQ
		createRecivedOrderVo.setDepartmentSeq(2L);
		// 会社SEQ
		createRecivedOrderVo.setCompanySeq(2L);
		// 見積SEQ
		createRecivedOrderVo.setQuotationSeq(39L);
		// 受注日
		createRecivedOrderVo.setRecivedOrderDate("20210503");
		
		
		List<CreateRecivedOrderDetailVo> details = new ArrayList<>();
		createRecivedOrderVo.setDetails(details);
		return createRecivedOrderVo;
	}
	
	
	/**
	 * エラー用（個数がマイナス）の商品データ生成
	 * @return
	 */
	private CreateRecivedOrderVo createErrorInputData2() {
		CreateRecivedOrderVo createRecivedOrderVo = new CreateRecivedOrderVo();
		
		// 取引先SEQ
		createRecivedOrderVo.setClientsSeq(2L);
		// 部門SEQ
		createRecivedOrderVo.setDepartmentSeq(2L);
		// 会社SEQ
		createRecivedOrderVo.setCompanySeq(2L);
		// 見積SEQ
		createRecivedOrderVo.setQuotationSeq(39L);
		// 受注日
		createRecivedOrderVo.setRecivedOrderDate("20210503");
		
		
		CreateRecivedOrderDetailVo createRecivedOrderDetailVo = new CreateRecivedOrderDetailVo();
		// 配送日
		createRecivedOrderDetailVo.setDeriveryDate("20210503");
		// 値引
		createRecivedOrderDetailVo.setDiscount(100L);
		// ロットSEQ
		createRecivedOrderDetailVo.setLotSeq(2L);
		// 商品SEQ
		createRecivedOrderDetailVo.setProductSeq(2L);
		// 数量
		createRecivedOrderDetailVo.setQuantity(-4);
		// 商品SEQ
		createRecivedOrderDetailVo.setStatus(0);
		
		List<CreateRecivedOrderDetailVo> details = new ArrayList<>();
		details.add(createRecivedOrderDetailVo);
		createRecivedOrderVo.setDetails(details);
		return createRecivedOrderVo;
	}
	
	
	/**
	 * デフォルトの取引先データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createDefaultClientsData() {
		// 	取得する商品の設定
		ClientsEntity clients = new ClientsEntity();
		clients.setClientsSeq(2L);
		return Optional.of(clients);
		
	}
	
	/**
	 * エラー用の取引先データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createErrorClientsData() {
		// 	取得する商品の設定
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトの会社データ生成
	 * @return
	 */
	private Optional<CompanyEntity> createDefaultCompanyData() {
		// 	取得する会社の設定
		CompanyEntity company = new CompanyEntity();
		company.setCompanySeq(2L);
		return Optional.of(company);
	}
	
	/**
	 * エラー用の会社データ生成
	 * @return
	 */
	private Optional<CompanyEntity> createErrorCompanyData() {
		// 	取得する会社の設定
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトの部署データ生成
	 * @return
	 */
	private Optional<DepartmentEntity> createDefaultDepartmentData() {
		// 	取得する商品の設定
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentSeq(2L);
		return Optional.of(department);	
	}
	
	/**
	 * エラー用の部署データ生成
	 * @return
	 */
	private Optional<DepartmentEntity> createErrorDepartmentData() {
		// 	取得する部署の設定
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトの商品データ生成
	 * @return
	 */
	private Optional<ProductEntity> createDefaultProductData() {
		// 	取得する商品の設定
		ProductEntity product = new ProductEntity();
		product.setUnitPrice(200L);
		return Optional.of(product);	
	}
	
	/**
	 * エラー用の商品データ生成
	 * @return
	 */
	private Optional<ProductEntity> createErrorProductData() {
		// 	取得する商品の設定
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトの見積データ生成
	 * @return
	 */
	private Optional<QuotationEntity> createDefaultQuotationData() {
		// 	取得する商品の設定
		QuotationEntity quotation = new QuotationEntity();
		quotation.setQuotationSeq(39L);
		return Optional.of(quotation);	
	}
	
	/**
	 * エラー用の見積データ生成
	 * @return
	 */
	private Optional<QuotationEntity> createErrorQuotationData() {
		// 	取得する見積の設定
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトのテストデータの場合の検証データ
	 * @return
	 */
	private RecivedOrderEntity createVerifyDataByDefaltInput() {
		RecivedOrderEntity entity = new RecivedOrderEntity();
		// 取引先SEQ
		entity.setClientsSeq(2L);
		// 部門SEQ
		entity.setDepartmentSeq(2L);
		// 会社SEQ
		entity.setCompanySeq(2L);
		// 見積SEQ
		entity.setQuotationSeq(39L);
		// 作成日
		entity.setRecivedOrderDate("20210503");
		
		
		RecivedOrderDetailEntity detailEntity = new RecivedOrderDetailEntity();
		// 商品SEQ
		detailEntity.setProductSeq(2L);
		// 値引
		detailEntity.setDiscount(100L);
		// 数量
		detailEntity.setQuantity(4);
		// 配送日
		detailEntity.setDeriveryDate("20210503");
		// 状態(必ず準備中にする)
		detailEntity.setStatus(BaseEnum.of(RacivedOrderStatus.class, 1));
		
		return entity;
	}

}
