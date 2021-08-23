
package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

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
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.LotEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.entity.SupplierProductEntity;
import com.erp.main.domain.objects.entity.WarehouseEntity;
import com.erp.main.domain.objects.model.ClientModel;
import com.erp.main.domain.objects.valueobjects.CreateClientsVo;
import com.erp.main.domain.objects.valueobjects.CreateCompanyVo;
import com.erp.main.domain.objects.valueobjects.CreateDepartmentVo;
import com.erp.main.domain.objects.valueobjects.CreateLotVo;
import com.erp.main.domain.objects.valueobjects.CreateProductVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.erp.main.domain.objects.valueobjects.CreateWarehouseVo;
import com.erp.main.domain.objects.valueobjects.GetClientVo;
import com.erp.main.domain.objects.valueobjects.UpdateClientVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.LotRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.SupplierProductRelationRepository;
import com.erp.main.domain.repository.SupplierProductsRepository;
import com.erp.main.domain.repository.SupplierRepository;
import com.erp.main.domain.repository.WarehouseRepository;
import com.erp.main.domain.services.MasterService;

/**
 * MasterServiceのパターン網羅用のテスト
 * @author takah
 *
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class MasterServiceTest {
	
	/**
	 * インジェクトするサービス
	 */
	@InjectMocks
	private MasterService masterService = new MasterService();
	
	/**
	 * 商品リポジトリ
	 */
	@Mock
	private ProductRepository productRepository;
	
	/**
	 * 仕入れ先リポジトリ
	 */
	@Mock
	private SupplierRepository supplierRepository;
	
	/**
	 * 仕入れ商品マスタのリポジトリ
	 */
	@Mock
	private SupplierProductsRepository supplierProductsRepository;
	
	/**
	 * 仕入れ商品関連マスタのリポジトリ
	 */
	@Mock
	private SupplierProductRelationRepository supplierProductRelationRepository;
	
	/**
	 * 取引先リポジトリ
	 */
	@Mock
	private ClientsRepository clientsRepository;
	
	/**
	 *会社リポジトリ
	 */
	@Mock
	private CompanyRepository companyRepository;
	
	/**
	 *部署リポジトリ
	 */
	@Mock
	private DepartmentRepository departmentRepository;
	
	/**
	 * 倉庫のリポジトリ
	 */
	@Mock
	private WarehouseRepository warehouseRepository;
	
	/*
	 * ロットのリポジトリ
	 */
	@Mock
	private LotRepository lotRepository;
	
	
	/**
	 * 商品作成用のテスト
	 */
	@Test
	public void registerProductSuccessCase1() {
		
		// 実行用のデータ作成
		CreateProductVo vo = new CreateProductVo();
		vo.setProductName("test商品");
		vo.setUnitPrice(1000L);
		
		// 処理実行
		this.masterService.createProduct(vo);
		
		// 検証用のデータ作成
		ProductEntity entity = new ProductEntity();
		entity.setName("test商品");
		entity.setUnitPrice(1000L);
		
		Mockito.verify(this.productRepository, times(1)).save(entity);
	}
	
	/**
	 * 倉庫作成用のテスト
	 */
	@Test
	public void registeWarehouseSuccessCase1() {
		
		// 実行用のデータ作成
		CreateWarehouseVo vo = new CreateWarehouseVo();
		vo.setWarehouseName("teszon");
		
		// 処理実行
		this.masterService.createWarehouse(vo);
		
		// 検証用のデータ作成
		WarehouseEntity entity = new WarehouseEntity();
		entity.setName("teszon");
		
		Mockito.verify(this.warehouseRepository, times(1)).save(entity);
	}
	
	/**
	 * 仕入れ先作成用のテスト
	 */
	@Test
	public void registeSupplierrSuccessCase1() {
		
		// 実行用のデータ作成
		CreateSupplierVo vo = new CreateSupplierVo();
		vo.setSupplierName("test社");
		
		// 処理実行
		this.masterService.createSupplier(vo);
		
		// 検証用のデータ作成
		SupplierEntity entity = new SupplierEntity();
		entity.setName("test社");
		
		Mockito.verify(this.supplierRepository, times(1)).save(entity);
	}
	
	/**
	 * 仕入商品作成用のテスト
	 * 成功例
	 */
	@Test
	void registeSupplierProductSuccessCase1() {
		// 実行用のデータ作成
		CreateSupplierProductVo vo = new CreateSupplierProductVo();
		// 取得処理をモック化
		Optional<SupplierEntity> supplier = this.createDefaultSupplierData();
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplier);
		
		// 仕入先Seq
		vo.setSupplierSeq(2L);
		// 仕入商品名
		vo.setSupplierProductName("test");
		// 値段
		vo.setPurchaseUnitPrice(2L);
		// 消費税
		vo.setTaxType(null);	
		
		var supplierProduct = this.createDefaultSupplierProductData();
		SupplierProductEntity ProductEntity = SupplierProductEntity.create(vo);
		Mockito.when(this.supplierProductsRepository.saveAndFlush(ProductEntity)).thenReturn(supplierProduct);
		
		
		// 処理実行
		this.masterService.createSupplierProduct(vo);
		
		
		// 検証用のデータ作成
		SupplierProductEntity entity = new SupplierProductEntity();
		
		// 仕入先Seq
		entity.setSupplierProductSeq(2L);
		// 仕入商品名
		entity.setName("test");
		// 値段
		entity.setPurchaseUnitPrice(2L);
		// 消費税
		entity.setTaxType(null);
		
		Mockito.verify(this.supplierProductsRepository, times(1)).saveAndFlush(entity);
		
	}
		
	/**
	 * 仕入商品作成用のテスト
	 * 失敗例
	 */
	@Test
	void registeSupplierProductErrorCase1() {
		// 実行用のデータ作成
		CreateSupplierProductVo vo = new CreateSupplierProductVo();
		// 取得処理をモック化
		Optional<SupplierEntity> supplier = this.createErrorSupplierData();
		Mockito.when(this.supplierRepository.findById(1L)).thenReturn(supplier);
		
		// 仕入先Seq
		vo.setSupplierSeq(1L);
		// 仕入商品名
		vo.setSupplierProductName("test");
		// 値段
		vo.setPurchaseUnitPrice(2L);
		// 消費税
		vo.setTaxType(null);	
		
		var supplierProduct = this.createDefaultSupplierProductData();
		SupplierProductEntity ProductEntity = SupplierProductEntity.create(vo);
		Mockito.when(this.supplierProductsRepository.saveAndFlush(ProductEntity)).thenReturn(supplierProduct);
	
		Assertions.assertThrows(AppException.class, () -> masterService.createSupplierProduct(vo));
	}

	/**
	 * ロット作成用のテスト
	 */
	@Test
	void registeLotSuccessCase1() {
		
		// 実行用のデータ作成
		var vo = new CreateLotVo();
		vo.setLotDate("20210617");
		vo.setLotNo("1234");		
		// 処理実行
		this.masterService.createLot(vo);
		
		// 検証用のデータ作成
		var entity = new LotEntity();
		entity.setLotNo("1234");
		entity.setLotDate("20210617");
		
		Mockito.verify(this.lotRepository, times(1)).save(entity);
	}
	
	
	/**
	 * 取引先作成用のテスト
	 */
	@Test
	public void registeClientsSuccessCase1() {
		
		// 実行用のデータ作成
		CreateClientsVo vo = new CreateClientsVo();
		vo.setClientsName("test社");
		
		// 処理実行
		this.masterService.createClients(vo);
		
		// 検証用のデータ作成
		ClientsEntity entity = new ClientsEntity();
		entity.setName("test社");
		
		Mockito.verify(this.clientsRepository, times(1)).save(entity);
	}
	
	/**
	 * 取引先詳細のテスト
	 * 通常パターン
	 */
	@Test
	public void getClientsSuccessCase1() {
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData();
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		this.masterService.getClientVo(2L);
		GetClientVo vo = GetClientVo.mapTo(clientsOpt.get());
		Assertions.assertEquals(vo, this.masterService.getClientVo(2L));
	}
	
	/**
	 * 取引先詳細のテスト
	 * 失敗パターン
	 * 取得されたIDが無かった場合
	 */
	@Test
	public void getClientsErrorCase1() {
		Optional<ClientsEntity> clientsOpt = this.createErorrClientsData();
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		Assertions.assertThrows(AppException.class, () -> masterService.getClientVo(2L));
	}
		
	
	/**
	 * 会社作成用のテスト
	 * 成功例
	 */
	@Test
	public void registeCompanySuccessCase1() {
		
		// 実行用のデータ作成
		CreateCompanyVo vo = new CreateCompanyVo();
		
		vo.setCompanyName("test社");
		vo.setCompanyZip("123-4567");
		vo.setCompanyAddress("三重県四日市市桜花台1-2-3");
		vo.setCompanyPhone("090-0000-0000");
		
		
		// 処理実行
		this.masterService.createCompany(vo);
		
		// 検証用のデータ作成
		CompanyEntity entity = new CompanyEntity();
		// 会社名
		entity.setName("test社");
		// 会社郵便番号
		entity.setZip("123-4567");
		// 会社住所
		entity.setAddress("三重県四日市市桜花台1-2-3");
		// 会社電話番号
		entity.setPhone("090-0000-0000");
		
		Mockito.verify(this.companyRepository, times(1)).save(entity);
	}
	
	/**
	 * 部署作成用のテスト
	 * 成功例
	 */
	@Test
	public void registeDepartmentSuccessCase1() {
		
		// 実行用のデータ作成
		CreateDepartmentVo vo = new CreateDepartmentVo();
		// 取得処理をモック化
		Optional<CompanyEntity> departmentOpt = this.createDefaultCompanyData();
		Mockito.when(this.companyRepository.findById(2L)).thenReturn(departmentOpt);
		
		// 会社Seq
		vo.setCompanySeq(2L);
		// 部署名
		vo.setDepartmentName("test部");
		
		
		// 処理実行
		this.masterService.createDepartment(vo);
		
		// 検証用のデータ作成
		DepartmentEntity entity = new DepartmentEntity();
		// 会社Seq
		entity.setCompanySeq(1L);
		// 部署名
		entity.setName("test部");
		
		Mockito.verify(this.departmentRepository, times(1)).save(entity);
	}
	
	/**
	 * 取引先編集処理用のテスト
	 * 成功例
	 */
	@Test
	void updateClientSuccessCase1() {
		
		// 実行用のデータ作成
		UpdateClientVo vo = new UpdateClientVo();
		// 取得処理をモック化
		Optional<ClientsEntity> clientsOpt = this.createDefaultClientsData2();
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		
		var client = new ClientModel();
		// 取引先名
		client.setClientsName("TEST");	
		// 取引先Seq
		client.setClientsSeq(2L);		
		// クライアントモデルセット
		vo.setClient(client);
		
		
		// 処理実行
		this.masterService.updateClient(vo);
		
		// 検証用のデータ作成
		ClientsEntity entity = new ClientsEntity();
		// 会社Seq
		entity.setClientsSeq(2L);
		// 部署名
		entity.setName("TEST");
		
		Mockito.verify(this.clientsRepository, times(1)).save(entity);
	}
	
	/**
	 * 取引先編集テスト
	 * 失敗例
	 */
	@Test
	void updateClientErrorCase1() {
		
		// 実行用のデータ作成
		UpdateClientVo vo = new UpdateClientVo();
		// 取得処理をモック化
		Optional<ClientsEntity> clientsOpt = this.createErrorClientsData();
		Mockito.when(this.clientsRepository.findById(2L)).thenReturn(clientsOpt);
		
		var client = new ClientModel();
		// 取引先名
		client.setClientsName("TEST");	
		// 取引先Seq
		client.setClientsSeq(1L);		
		// クライアントモデルセット
		vo.setClient(client);
		
		Assertions.assertThrows(AppException.class, () -> masterService.updateClient(vo));
		

	}
	
	/**
	 * デフォルトの会社データ生成
	 * @return
	 */
	private Optional<CompanyEntity> createDefaultCompanyData() {
		// 	取得する会社Seqの設定
		CompanyEntity department = new CompanyEntity();
		department.setCompanySeq(1L);
		return Optional.of(department);
		
	}

	/**
	 * 会社作成用のテスト
	 * 失敗例
	 */
	@Test
	public void registeDepartmentErrorCase1() {
		
		// 実行用のデータ作成
		CreateDepartmentVo vo = new CreateDepartmentVo();
		// 取得処理をモック化
		Optional<DepartmentEntity> departmentOpt = this.createDefaultCompanyData2();
		Mockito.when(this.departmentRepository.findById(1L)).thenReturn(departmentOpt);
		
		// 会社Seq
		vo.setCompanySeq(1L);
		// 部署名
		vo.setDepartmentName("test部");
		
		Assertions.assertThrows(AppException.class, () -> masterService.createDepartment(vo));

	}
	
	/**
	 * エラー時の仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createErrorSupplierData() {
		// 	取得する仕入先Seqの設定
		return Optional.empty();
	}
	
	/**
	 * デフォルトの仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createDefaultSupplierData() {
		// 	取得する仕入先Seqの設定
		SupplierEntity entity = new SupplierEntity();
		entity.setSupplierSeq(2L);
		return Optional.of(entity);
	}
	/**
	 * デフォルトの仕入商品データ生成
	 * @return 
	 * @return
	 */
	private SupplierProductEntity createDefaultSupplierProductData() {
		// 	取得する会社Seqの設定
		SupplierProductEntity entity = new SupplierProductEntity();
		// 仕入先Seq
		entity.setSupplierProductSeq(2L);
		// 仕入商品名
		entity.setName("test");
		// 値段
		entity.setPurchaseUnitPrice(2L);
		// 消費税
		entity.setTaxType(null);
		return entity;
	}
	
	/**
	 * デフォルトの会社データ生成
	 * @return
	 */
	private Optional<DepartmentEntity> createDefaultCompanyData2() {
		// 	取得する会社Seqの設定
		DepartmentEntity department = new DepartmentEntity();
		department.setCompanySeq(null);
		return Optional.empty();
		
	}
	
	/**
	 * デフォルトの取引先データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createDefaultClientsData() {
		// 	取得する会社Seqの設定
		ClientsEntity clients = new ClientsEntity();
		clients.setClientsSeq(2L);
		clients.setName("test");
		return Optional.of(clients);
		
	}
	
	/**
	 * デフォルトの取引先編集データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createDefaultClientsData2() {
		// 	取得する会社Seqの設定
		ClientsEntity clients = new ClientsEntity();
		clients.setClientsSeq(2L);
		clients.setName("TEST");
		return Optional.of(clients);
		
	}
	
	/**
	 * エラー用の取引先編集データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createErrorClientsData() {
		// 	取得する会社Seqの設定
		return Optional.empty();
		
	}
	
	/**
	 * エラー用の取引先データ生成
	 * @return
	 */
	private Optional<ClientsEntity> createErorrClientsData() {
		return Optional.empty();
		
	}
	
}
