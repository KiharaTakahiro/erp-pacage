
package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

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
import com.erp.main.domain.objects.model.CompanyModel;
import com.erp.main.domain.objects.model.DepartmentModel;
import com.erp.main.domain.objects.model.SupplierModel;
import com.erp.main.domain.objects.model.WarehouseModel;
import com.erp.main.domain.objects.valueobjects.CreateClientsVo;
import com.erp.main.domain.objects.valueobjects.CreateCompanyVo;
import com.erp.main.domain.objects.valueobjects.CreateDepartmentVo;
import com.erp.main.domain.objects.valueobjects.CreateLotVo;
import com.erp.main.domain.objects.valueobjects.CreateProductVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.erp.main.domain.objects.valueobjects.CreateWarehouseVo;
import com.erp.main.domain.objects.valueobjects.GetClientVo;
import com.erp.main.domain.objects.valueobjects.GetClientsConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetClientsVo;
import com.erp.main.domain.objects.valueobjects.GetCompanysVo;
import com.erp.main.domain.objects.valueobjects.GetDepartmentConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetDepartmentsVo;
import com.erp.main.domain.objects.valueobjects.GetProductVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierVo;
import com.erp.main.domain.objects.valueobjects.GetSuppliersVo;
import com.erp.main.domain.objects.valueobjects.GetWarehouseVo;
import com.erp.main.domain.objects.valueobjects.UpdateClientVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierVo;
import com.erp.main.domain.objects.valueobjects.UpdateWarehouseVo;
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
import com.erp.main.domain.specification.ClientsSpec;
import com.erp.main.domain.specification.DepartmentSpec;

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
	 * 商品詳細のテスト
	 * 通常パターン
	 */
	@Test
	public void getProductSuccessCase1() {
		Optional<ProductEntity> productOpt = this.createDefaultProductData();
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		this.masterService.getProductVo(2L);
		GetProductVo vo = GetProductVo.mapTo(productOpt.get());
		Assertions.assertEquals(vo, this.masterService.getProductVo(2L));
	}
	
	/**
	 * 商品詳細のテスト
	 * 失敗パターン
	 * 取得されたIDが無かった場合
	 */
	@Test
	public void getProductErrorCase1() {
		Optional<ProductEntity> productOpt = this.createErrorProductData();
		Mockito.when(this.productRepository.findById(2L)).thenReturn(productOpt);
		Assertions.assertThrows(AppException.class, () -> masterService.getProductVo(2L));
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
	 * 仕入先詳細のテスト
	 * 通常パターン
	 */
	@Test
	public void getSupplierSuccessCase1() {
		Optional<SupplierEntity> supplierOpt = this.createDefaultSupplierData2();
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplierOpt);
		GetSupplierVo vo = GetSupplierVo.mapTo(supplierOpt.get());
		Assertions.assertEquals(vo, this.masterService.getSupplierVo(2L));
	}
	
	/**
	 * 仕入先詳細のテスト
	 * 失敗パターン
	 * 取得されたIDが無かった場合
	 */
	@Test
	public void getSupplierErrorCase1() {
		Optional<SupplierEntity> supplierOpt = this.createErorrSupplierData();
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplierOpt);
		Assertions.assertThrows(AppException.class, () -> masterService.getSupplierVo(2L));
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
	 * 仕入先編集処理用のテスト
	 * 成功例
	 */
	@Test
	void updateSupplierSuccessCase1() {
		
		// 実行用のデータ作成
		UpdateSupplierVo vo = new UpdateSupplierVo();
		// 取得処理をモック化
		Optional<SupplierEntity> supplierOpt = this.createDefaultSupplierData2();
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplierOpt);
		
		var supplier = new SupplierModel();
		// 仕入先名
		supplier.setSupplierName("TEST");	
		// 仕入先Seq
		supplier.setSupplierSeq(2L);		
		vo.setSupplier(supplier);
		
		
		// 処理実行
		this.masterService.updateSupplier(vo);
		
		// 検証用のデータ作成
		SupplierEntity entity = new SupplierEntity();
		// 仕入先Seq
		entity.setSupplierSeq(2L);
		// 仕入先名名
		entity.setName("TEST");
		
		Mockito.verify(this.supplierRepository, times(1)).save(entity);
	}
	
	/**
	 * 仕入先編集テスト
	 * 失敗例
	 */
	@Test
	void updateSupplierErrorCase1() {
		
		// 実行用のデータ作成
		UpdateSupplierVo vo = new UpdateSupplierVo();
		// 取得処理をモック化
		Optional<SupplierEntity> supplierOpt = this.createErrorSupplierData();
		Mockito.when(this.supplierRepository.findById(2L)).thenReturn(supplierOpt);
		
		var supplier = new SupplierModel();
		// 仕入先名
		supplier.setSupplierName("TEST");	
		// 仕入先Seq
		supplier.setSupplierSeq(1L);		
		// クライアントモデルセット
		vo.setSupplier(supplier);
		
		Assertions.assertThrows(AppException.class, () -> masterService.updateSupplier(vo));
		

	}
	
	/*
	 * 仕入先一覧プルダウン取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getSuppliersSuccessCase3() {
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "supplierSeq");
		List<SupplierEntity> entitys = this.createDefaultSupplierData3();
		Mockito.when(this.supplierRepository.findAll(sort)).thenReturn(entitys);
		
		// 値格納用のリスト作成
		List<SupplierModel> suppliers =  new ArrayList<>();
	
		var supplier = new SupplierModel();
		// 仕入先SEQ
		supplier.setSupplierSeq(1L);
		// 仕入先名
		supplier.setSupplierName("test");
		// リストに追加
		suppliers.add(supplier);
	
		var vo = new GetSuppliersVo();
		// 仕入先リストの設定
		vo.setSupplier(suppliers);
		
		Assertions.assertEquals(vo, this.masterService.pullDownSupplier());
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
		// 取引先Seq
		entity.setClientsSeq(2L);
		// 取引先名
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
		
	/*
	 * 取引先一覧取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getClientsSuccessCase2() {
		// 検索条件
		GetClientsConditionsVo condition = new GetClientsConditionsVo();
		condition.setPageNo(0);
		condition.setClientsName("TEST");
		condition.setClientsSeq(2L);
		
		Specification<ClientsEntity> spec = Specification.where(
				ClientsSpec.clientsSeqEquals(condition.getClientsSeq()))
				.and(ClientsSpec.clientsNameEquals(condition.getClientsName()));
		
		var sort = Sort.by(Sort.Direction.ASC, "clientsSeq");
		
		Page<ClientsEntity> pages = this.createDefaultClientsData3();
		Mockito.when(this.clientsRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort))).thenReturn(pages);
		
		// 検証用データ作成
		List<ClientModel> clients = pages.get().map(e -> {
			var client = new ClientModel();
			// 取引先Seq
			client.setClientsSeq(e.getClientsSeq());
			// 取引先名
			client.setClientsName(e.getName());
			return client;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetClientsVo();
		// 総データ数
		vo.setTotalItemsNum(pages.getTotalElements());
		// 取引先リストの設定
		vo.setClients(clients);

		Assertions.assertEquals(vo, this.masterService.getClientsVo(condition));
	}
	
	/*
	 * 取引先一覧取得 失敗例
	 * @param condition
	 * @return
	 */
	@Test
	public void getClientsErrorCase2() {
		// 検索条件
		GetClientsConditionsVo condition = new GetClientsConditionsVo();
		condition.setPageNo(null);
		condition.setClientsName("TEST");
		condition.setClientsSeq(2L);
		Assertions.assertThrows(AppException.class, () -> masterService.getClientsVo(condition));
	}
	
	/*
	 * 取引先一覧プルダウン取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getClientsSuccessCase3() {
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "clientsSeq");
		List<ClientsEntity> entitys = this.createDefaultClientsData4();
		Mockito.when(this.clientsRepository.findAll(sort)).thenReturn(entitys);
		
		// 値格納用のリスト作成
		List<ClientModel> clients =  new ArrayList<>();
	
		var client = new ClientModel();
		// 取引先SEQ
		client.setClientsSeq(1L);
		// 取引先名
		client.setClientsName("test");
		// リストに追加
		clients.add(client);
	
		var vo = new GetClientsVo();
		// 取引先リストの設定
		vo.setClients(clients);
		
		Assertions.assertEquals(vo, this.masterService.pullDownClients());
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
	
	/*
	 * 会社一覧プルダウン取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getCompanysSuccessCase3() {
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "companySeq");
		List<CompanyEntity> entitys = this.createDefaultCompanysData2();
		Mockito.when(this.companyRepository.findAll(sort)).thenReturn(entitys);
		
		// 値格納用のリスト作成
		List<CompanyModel> companys =  new ArrayList<>();
	
		var company = new CompanyModel();
		// 会社SEQ
		company.setCompanySeq(1L);
		// 会社名
		company.setCompanyName("test");
		// リストに追加
		companys.add(company);
	
		var vo = new GetCompanysVo();
		// 会社リストの設定
		vo.setCompany(companys);
		
		Assertions.assertEquals(vo, this.masterService.pullDownCompany());
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
	
	/*
	 * 部署一覧プルダウン取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getDepartmentsSuccessCase() {
		// 検索条件
		GetDepartmentConditionsVo condition = new GetDepartmentConditionsVo();
		condition.setPageNo(1);
		condition.setDepartmentName("TEST");
		condition.setDepartmentSeq(2L);
		// 検索条件の設定
		Specification<DepartmentEntity> spec = Specification.where(
			DepartmentSpec.departmentSeqEquals(condition.getDepartmentSeq())
				.and(DepartmentSpec.companySeqEquals(condition.getCompanySeq()))
				.and(DepartmentSpec.departmentNameEquals(condition.getDepartmentName())));
					
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "departmentSeq");
		List<DepartmentEntity> entitys = this.createDefaultDepartmentsData2();
		Mockito.when(this.departmentRepository.findAll(spec, sort)).thenReturn(entitys);
		
		// 値格納用のリスト作成
		List<DepartmentModel> departments =  new ArrayList<>();
	
		var department = new DepartmentModel();
		// 部署SEQ
		department.setDepartmentSeq(1L);
		// 部署名
		department.setDepartmentName("test");
		// リストに追加
		departments.add(department);
	
		var vo = new GetDepartmentsVo();
		// 部署リストの設定
		vo.setDepartment(departments);
		
		Assertions.assertEquals(vo, this.masterService.pullDownDepartment(condition));
	}
	
	/**
	 * 倉庫詳細のテスト
	 * 通常パターン
	 */
	@Test
	public void getWarehouseSuccessCase1() {
		Optional<WarehouseEntity> warehouseOpt = this.createDefaultWarehouseData();
		Mockito.when(this.warehouseRepository.findById(2L)).thenReturn(warehouseOpt);
		this.masterService.getWarehouseVo(2L);
		GetWarehouseVo vo = GetWarehouseVo.mapTo(warehouseOpt.get());
		Assertions.assertEquals(vo, this.masterService.getWarehouseVo(2L));
	}
	
	/**
	 * 倉庫詳細のテスト
	 * 失敗パターン
	 * 取得されたIDが無かった場合
	 */
	@Test
	public void getWarehouseErrorCase1() {
		Optional<WarehouseEntity> warehouseOpt = this.createErorrWarehouseData();
		Mockito.when(this.warehouseRepository.findById(2L)).thenReturn(warehouseOpt);
		Assertions.assertThrows(AppException.class, () -> masterService.getWarehouseVo(2L));
	}
	/**
	 * 倉庫編集処理用のテスト
	 * 成功例
	 */
	@Test
	void updateWarehouseSuccessCase1() {
		
		// 実行用のデータ作成
		UpdateWarehouseVo vo = new UpdateWarehouseVo();
		// 取得処理をモック化
		Optional<WarehouseEntity> warehouseOpt = this.createDefaultWarehouseData2();
		Mockito.when(this.warehouseRepository.findById(2L)).thenReturn(warehouseOpt);
		
		var warehouse = new WarehouseModel();
		// 倉庫名
		warehouse.setWarehouseName("TEST");	
		// 倉庫Seq
		warehouse.setWarehouseSeq(2L);		
		vo.setWarehouse(warehouse);
		
		
		// 処理実行
		this.masterService.updateWarehouse(vo);
		
		// 検証用のデータ作成
		WarehouseEntity entity = new WarehouseEntity();
		// 倉庫Seq
		entity.setWarehouseSeq(2L);
		// 倉庫名名
		entity.setName("TEST");
		
		Mockito.verify(this.warehouseRepository, times(1)).save(entity);
	}
	
	/**
	 * 倉庫編集テスト
	 * 失敗例
	 */
	@Test
	void updateWarehouseErrorCase1() {
		
		// 実行用のデータ作成
		UpdateWarehouseVo vo = new UpdateWarehouseVo();
		// 取得処理をモック化
		Optional<WarehouseEntity> warehouseOpt = this.createErrorWarehouseData();
		Mockito.when(this.warehouseRepository.findById(2L)).thenReturn(warehouseOpt);
		
		var warehouse = new WarehouseModel();
		// 倉庫名
		warehouse.setWarehouseName("TEST");	
		// 倉庫Seq
		warehouse.setWarehouseSeq(1L);		
		// クライアントモデルセット
		vo.setWarehouse(warehouse);
		
		Assertions.assertThrows(AppException.class, () -> masterService.updateWarehouse(vo));
		

	}
	
	/*
	 * 倉庫一覧プルダウン取得 成功例
	 * @param condition
	 * @return
	 */
	@Test
	public void getWarehousesSuccessCase3() {
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "warehouseSeq");
		List<WarehouseEntity> entitys = this.createDefaultWarehousesData3();
		Mockito.when(this.warehouseRepository.findAll(sort)).thenReturn(entitys);
		
		// 値格納用のリスト作成
		List<WarehouseModel> warehouses =  new ArrayList<>();
	
		var warehouse = new WarehouseModel();
		// 倉庫SEQ
		warehouse.setWarehouseSeq(1L);
		// 倉庫名
		warehouse.setWarehouseName("test");
		// リストに追加
		warehouses.add(warehouse);
	
		var vo = new GetWarehouseVo();
		// 倉庫リストの設定
		vo.setWarehouse(warehouse);
		
		Assertions.assertEquals(vo, this.masterService.pullDownWarehouse());
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
	 * デフォルトの商品データ作成
	 * 
	 */
	private Optional<ProductEntity> createDefaultProductData(){
		ProductEntity entity = new ProductEntity();
		entity.setProductSeq(2L);
		return Optional.of(entity);
	}
	
	/**
	 * エラー時の商品データ作成
	 * 
	 */
	private Optional<ProductEntity> createErrorProductData(){
		return Optional.empty();
	}

	
	/**
	 * デフォルトの仕入先一覧プルダウンデータ生成
	 * @return
	 */
	private List<SupplierEntity> createDefaultSupplierData3() {
		// 	取得する仕入先Seqの設定
		List<SupplierEntity> suppliers = new ArrayList<>();
		var supplier = new SupplierEntity();
		// 仕入先SEQ
		supplier.setSupplierSeq(1L);
		// 仕入先名
		supplier.setName("test");
		// リストに追加
		suppliers.add(supplier);
		return suppliers;
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
	 * デフォルトの取引先一覧データ生成
	 * @return
	 */
	private Page<ClientsEntity> createDefaultClientsData3() {
		// 	取得する取引先Seqの設定
		List<ClientsEntity> clients = new ArrayList<>();
		var client = new ClientsEntity();
		// 取引先SEQ
		client.setClientsSeq(2L);
		// 取引先名
		client.setName("TEST");
		// リストに追加
		clients.add(client);
		Page<ClientsEntity> pages = new PageImpl<ClientsEntity>(clients);
		return pages;
	}
	
	/**
	 * デフォルトの取引先一覧プルダウンデータ生成
	 * @return
	 */
	private List<ClientsEntity> createDefaultClientsData4() {
		// 	取得する取引先Seqの設定
		List<ClientsEntity> clients = new ArrayList<>();
		var client = new ClientsEntity();
		// 取引先SEQ
		client.setClientsSeq(1L);
		// 取引先名
		client.setName("test");
		// リストに追加
		clients.add(client);
		return clients;
	}
	
	/**
	 * デフォルトの会社一覧プルダウンデータ生成
	 * @return
	 */
	private List<CompanyEntity> createDefaultCompanysData2() {
		// 	取得する会社Seqの設定
		List<CompanyEntity> companys = new ArrayList<>();
		var company = new CompanyEntity();
		// 会社SEQ
		company.setCompanySeq(1L);
		// 会社名
		company.setName("test");
		// リストに追加
		companys.add(company);
		return companys;
	}
	
	/**
	 * デフォルトの倉庫一覧プルダウンデータ生成
	 * @return
	 */
	private List<WarehouseEntity> createDefaultWarehousesData3() {
		// 	取得する倉庫Seqの設定
		List<WarehouseEntity> warehouses = new ArrayList<>();
		var warehouse = new WarehouseEntity();
		// 倉庫SEQ
		warehouse.setWarehouseSeq(1L);
		// 倉庫名
		warehouse.setName("test");
		// リストに追加
		warehouses.add(warehouse);
		return warehouses;
	}
	
	/**
	 * デフォルトの倉庫編集データ生成
	 */
	private Optional<WarehouseEntity> createDefaultWarehouseData2() {
		// 	取得する倉庫Seqの設定
		WarehouseEntity warehouse = new WarehouseEntity();
		warehouse.setWarehouseSeq(2L);
		warehouse.setName("TEST");
		return Optional.of(warehouse);
	}
	
	/**
	 * デフォルトの倉庫データ生成
	 * @return
	 */
	private Optional<WarehouseEntity> createDefaultWarehouseData() {
		// 	取得する会社Seqの設定
		WarehouseEntity warehouse = new WarehouseEntity();
		warehouse.setWarehouseSeq(2L);
		warehouse.setName("test");
		return Optional.of(warehouse);
		
	}
	
	/**
	 * デフォルトの部署一覧プルダウンデータ生成
	 * @return
	 */
	private List<DepartmentEntity> createDefaultDepartmentsData2() {
		// 	取得する部署Seqの設定
		List<DepartmentEntity> departments = new ArrayList<>();
		var department = new DepartmentEntity();
		// 部署SEQ
		department.setDepartmentSeq(1L);
		// 部署名
		department.setName("test");
		// リストに追加
		departments.add(department);
		return departments;
	}
	
  
	/**
	 * デフォルトの仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createDefaultSupplierData2() {
		// 	取得する会社Seqの設定
		SupplierEntity supplier = new SupplierEntity();
		supplier.setSupplierSeq(2L);
		supplier.setName("test");
		return Optional.of(supplier);
		
	}
  
	/**
	 * エラー用の仕入先データ生成
	 * @return
	 */
	private Optional<SupplierEntity> createErorrSupplierData() {
		return Optional.empty();
		
	}
	
	/**
	 * エラー用の倉庫データ生成
	 * @return
	 */
	private Optional<WarehouseEntity> createErorrWarehouseData() {
		return Optional.empty();
		
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
	
	/**
	 * エラー用の倉庫編集データ生成
	 */
	private Optional<WarehouseEntity> createErrorWarehouseData() {
		// 	取得する倉庫Seqの設定
		return Optional.empty();
		
	}
	

	
}
