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
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo.CreateQuotationDetailVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.QuotationRepository;
import com.erp.main.domain.services.QuotationService;

/**
 * UserServiceのパターン網羅用のテスト
 * @author takah
 *
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {
	
	@InjectMocks
	private QuotationService quotationService = new QuotationService();
	
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
	
	/**
	 * 正常系
	 * 通常の登録ケース
	 */
	@Test
	public void createQuotationSuccessCase1() {
		// 実行用テストデータの作成
		CreateQuotationVo createQuotationVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L)).thenReturn(70L);
		
		// 処理の実行
		this.quotationService.createQuotation(createQuotationVo);
		
		// 検証用データの作成
		QuotationEntity entity = this.createVerifyDataByDefaltInput();
		// 消費税
		entity.setTax(70L);
		// 合計金額
		entity.setTotal(770L);
		// 値の検証
		Mockito.verify(this.quotationRepository, times(1)).save(entity);
	}
	
	// TODO: テストケースが足りていないため今後追加する
	

	/**
	 * 異常系1
	 * 取引先情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase1() {
		// 実行用テストデータの作成
		CreateQuotationVo createQuotationVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createErrorClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> quotationService.createQuotation(createQuotationVo));
	}
	
	/**
	 * 異常系2
	 * 会社情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase2() {
		// 実行用テストデータの作成
		CreateQuotationVo createQuotationVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createErrorCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> quotationService.createQuotation(createQuotationVo));
	}
	
	/**
	 * 異常系3
	 * 部署情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase3() {
		// 実行用テストデータの作成
		CreateQuotationVo createQuotationVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createErrorDepartmentData();
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> quotationService.createQuotation(createQuotationVo));
	}
	
	/**
	 * 異常系4
	 * 商品情報がないパターン
	 */
	@Test
	public void createQuotationErrorCase4() {
		// 実行用テストデータの作成
		CreateQuotationVo createQuotationVo = this.createDefaultInputData();
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Optional<CompanyEntity> companyOpt = this.createDefaultCompanyData();
		Optional<DepartmentEntity> departmentOpt = this.createDefaultDepartmentData();
		Optional<ProductEntity> productOpt = this.createErrorProductData();
		
		// 取得処理をモック化(取引先情報)
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		// 取得処理をモック化(会社情報)
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(companyOpt);
		// 取得処理をモック化(部署情報)
		Mockito.when(this.departmentRepository.findById(2L)).thenReturn(departmentOpt);
		// 取得処理をモック化(商品情報)
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		// 消費税はサービスのテストでは10%として考える
		Mockito.when(this.moneyComponent.computeTax(700L)).thenReturn(70L);
		
		// 処理の実行
		Assertions.assertThrows(AppException.class, () -> quotationService.createQuotation(createQuotationVo));
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
		// 	取得する商品の設定
		CompanyEntity company = new CompanyEntity();
		company.setCompanySeq(2L);
		return Optional.of(company);
	}
	
	/**
	 * エラー用の会社データ生成
	 * @return
	 */
	private Optional<CompanyEntity> createErrorCompanyData() {
		// 	取得する商品の設定
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
		// 	取得する商品の設定
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
	 * デフォルトの商品データ生成
	 * @return
	 */
	private CreateQuotationVo createDefaultInputData() {
		CreateQuotationVo createQuotationVo = new CreateQuotationVo();
		// 取引先SEQ
		createQuotationVo.setClientsSeq(2L);
		// 部門SEQ
		createQuotationVo.setDepartmentSeq(2L);
		// 会社SEQ
		createQuotationVo.setCompanySeq(2L);
		// 見積番号
		createQuotationVo.setQuotationNo("mitsumori-1");
		// 作成日
		createQuotationVo.setCreateDate("20210503");
		// 件名
		createQuotationVo.setSubject("subject");
		// 値引合計
		createQuotationVo.setDiscountTotal(100L);
		
		CreateQuotationDetailVo createQuotationDetailVo = new CreateQuotationDetailVo();
		// 商品SEQ
		createQuotationDetailVo.setProductSeq(2L);
		// 値引
		createQuotationDetailVo.setDiscount(100L);
		// 数量
		createQuotationDetailVo.setQuantity(4);
		
		List<CreateQuotationDetailVo> details = new ArrayList<>();
		details.add(createQuotationDetailVo);
		createQuotationVo.setDetails(details);
		return createQuotationVo;
	}
	
	/**
	 * デフォルトのテストデータの場合の検証データ
	 * @return
	 */
	private QuotationEntity createVerifyDataByDefaltInput() {
		QuotationEntity entity = new QuotationEntity();
		// 取引先SEQ
		entity.setClientsSeq(2L);
		// 部門SEQ
		entity.setDepartmentSeq(2L);
		// 会社SEQ
		entity.setCompanySeq(2L);
		// 見積番号
		entity.setQuotationNo("mitsumori-1");
		// 作成日
		entity.setCreateDate("20210503");
		// 件名
		entity.setSubject("subject");
		// 値引合計
		entity.setDiscountTotal(100L);
		// 小計
		entity.setSubTotal(700L);
		
		QuotationDetailEntity detailEntity = new QuotationDetailEntity();
		// 商品SEQ
		detailEntity.setProductSeq(2L);
		// 値引
		detailEntity.setDiscount(100L);
		// 数量
		detailEntity.setQuantity(4);
		// 金額 (単金200円 × 数量4個 - 値引100円のため)
		detailEntity.setPrice(700L);
		
		return entity;
	}
	
	
}
