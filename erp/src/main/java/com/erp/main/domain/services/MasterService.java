package com.erp.main.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.LotEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.entity.SupplierProductEntity;
import com.erp.main.domain.objects.entity.SupplierProductRelationEntity;
import com.erp.main.domain.objects.entity.WarehouseEntity;
import com.erp.main.domain.objects.model.ClientModel;
import com.erp.main.domain.objects.model.CompanyModel;
import com.erp.main.domain.objects.model.DepartmentModel;
import com.erp.main.domain.objects.model.ProductModel;
import com.erp.main.domain.objects.model.ProductTableModel;
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
import com.erp.main.domain.objects.valueobjects.GetProductConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetProductVo;
import com.erp.main.domain.objects.valueobjects.GetProductsTableVo;
import com.erp.main.domain.objects.valueobjects.GetProductsVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetSupplierVo;
import com.erp.main.domain.objects.valueobjects.GetSuppliersVo;
import com.erp.main.domain.objects.valueobjects.GetWarehouseConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetWarehouseVo;
import com.erp.main.domain.objects.valueobjects.GetWarehousesVo;
import com.erp.main.domain.objects.valueobjects.SupplierProductRelationVo;
import com.erp.main.domain.objects.valueobjects.UpdateClientVo;
import com.erp.main.domain.objects.valueobjects.UpdateProductVo;
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
import com.erp.main.domain.specification.ClientsSpec;
import com.erp.main.domain.specification.DepartmentSpec;
import com.erp.main.domain.specification.ProductSpec;
import com.erp.main.domain.specification.SupplierSpec;
import com.erp.main.domain.specification.WarehouseSpec;

/**
 * マスターの管理用のサービス
 * @author 木原
 *
 */
@Service
public class MasterService {
	
	/**
	 * 商品マスタのリポジトリ
	 */
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 仕入れ先マスタのリポジトリ
	 */
	@Autowired
	private SupplierRepository supplierRepository;
	
	/**
	 * 仕入れ商品マスタのリポジトリ
	 */
	@Autowired
	private SupplierProductsRepository supplierProductsRepository;
	
	/**
	 * 仕入れ商品関連マスタのリポジトリ
	 */
	@Autowired
	private SupplierProductRelationRepository supplierProductRelationRepository;
	
	/**
	 * 取引先マスタのリポジトリ
	 */
	@Autowired
	private ClientsRepository clientsRepository;
	
	/**
	 * 会社マスタのリポジトリ
	 */
	@Autowired
	private CompanyRepository companyRepository;
	
	/**
	 * 部署のリポジトリ
	 */
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * 倉庫のリポジトリ
	 */
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	/*
	 * ロットのレポジトリ
	 */
	
	@Autowired
	private LotRepository lotRepository;
	
	/**
	 * 商品マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createProduct(CreateProductVo vo) {
		ProductEntity entity = ProductEntity.create(vo);
		this.productRepository.save(entity);
		
	}
	
	/**
	 * 仕入れ先マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createSupplier(CreateSupplierVo vo) {
		SupplierEntity entity = SupplierEntity.create(vo);
		this.supplierRepository.save(entity);
		
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
		var supplierProductEntity = this.supplierProductsRepository.saveAndFlush(entity);
		
		
		var relationVo = new SupplierProductRelationVo();
		relationVo.setSupplierSeq(vo.getSupplierSeq());
		relationVo.setSupplierProductSeq(supplierProductEntity.getSupplierProductSeq());

		SupplierProductRelationEntity relationEntity = SupplierProductRelationEntity.create(relationVo);
		this.supplierProductRelationRepository.save(relationEntity);
		
		
	}
	
	/**
	 * 取引先マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createClients(CreateClientsVo vo) {
		ClientsEntity entity = ClientsEntity.create(vo);
		this.clientsRepository.save(entity);
		
	}
	
	/**
	 * 会社マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createCompany(CreateCompanyVo vo) {
		CompanyEntity entity = CompanyEntity.create(vo);
		this.companyRepository.save(entity);
		
	}
	
	/**
	 * 部署マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createDepartment(CreateDepartmentVo vo) {
		Optional<CompanyEntity> product = this.companyRepository.findById(vo.getCompanySeq());
		if(product.isEmpty()) {
			throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",vo.getCompanySeq()));
		}
		
		DepartmentEntity entity = DepartmentEntity.create(vo);
		this.departmentRepository.save(entity);
		
	}
	
	/**
	 * 倉庫マスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createWarehouse(CreateWarehouseVo vo) {
		WarehouseEntity entity = WarehouseEntity.create(vo);
		this.warehouseRepository.save(entity);
		
	}
	
	/**
	 * ロットマスタ作成処理
	 * @param vo
	 */
	@Transactional
	public void createLot(CreateLotVo vo) {
		var entity = LotEntity.create(vo);
		this.lotRepository.save(entity);
		
	}

	/**
	 * クライアント詳細画面のレスポンス
	 * @param vo
	 */
	@Transactional
	public GetClientVo getClientVo(Long clientsSeq){
		Optional<ClientsEntity> client = clientsRepository.findById(clientsSeq);
		if(client.isEmpty()) {
			throw new AppException(String.format("該当の取引先を取得できませんでした。 clientsSeq: %s", clientsSeq));
		}
		
		return GetClientVo.mapTo(client.get());
	
	}
	
	/*
	 * クライアント編集完了処理
	 * @param vo
	 */
	@Transactional
	public void updateClient(UpdateClientVo vo) {
		Optional<ClientsEntity> client = clientsRepository.findById(vo.getClient().getClientsSeq());
		if(client.isEmpty()) {
			throw new AppException(String.format("該当の取引先を取得できませんでした。 client: %s", client));
		}
		
		var clientEntity = client.get();
		clientEntity.update(vo);	
		this.clientsRepository.save(clientEntity);
	}
	
	/*
	 * 取引先一覧取
	 * @param condition
	 * @return
	 */
	@Transactional
	public GetClientsVo getClientsVo(GetClientsConditionsVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<ClientsEntity> spec = Specification.where(
				ClientsSpec.clientsSeqEquals(condition.getClientsSeq()))
				.and(ClientsSpec.clientsNameEquals(condition.getClientsName()));
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "clientsSeq");
		
		Page<ClientsEntity> pages = this.clientsRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
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
		
		return vo;
	}
	
	/*
	 * クライアント一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetClientsVo pullDownClients() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "clientsSeq");
		
		// 取引先一覧取得
		List<ClientsEntity> entities = this.clientsRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<ClientModel> clients =  new ArrayList<>();
		
		for(ClientsEntity entity: entities) {		
			var client = new ClientModel();
			// 取引先SEQ
			client.setClientsSeq(entity.getClientsSeq());
			// 取引先名
			client.setClientsName(entity.getName());
			// リストに追加
			clients.add(client);
			
		}
	
		var vo = new GetClientsVo();
		// 取引先リストの設定
		vo.setClients(clients);
		
		return vo;
	}
	
	/*
	 * 会社一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetCompanysVo pullDownCompany() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "companySeq");
		
		// 取引先一覧取得
		List<CompanyEntity> entities = this.companyRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<CompanyModel> companys =  new ArrayList<>();
		
		for(CompanyEntity entity: entities) {		
			var company = new CompanyModel();
			// 取引先SEQ
			company.setCompanySeq(entity.getCompanySeq());
			// 取引先名
			company.setCompanyName(entity.getName());
			// リストに追加
			companys.add(company);
			
		}
	
		var vo = new GetCompanysVo();
		// 取引先リストの設定
		vo.setCompany(companys);
		
		return vo;
	}
	
	/*
	 * 部署一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetDepartmentsVo pullDownDepartment(GetDepartmentConditionsVo condition) {
		// 検索条件の設定
		Specification<DepartmentEntity> spec = Specification.where(
			DepartmentSpec.departmentSeqEquals(condition.getDepartmentSeq()))
				.and(DepartmentSpec.companySeqEquals(condition.getCompanySeq()))
				.and(DepartmentSpec.departmentNameEquals(condition.getDepartmentName()));
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "departmentSeq");
		
		// 取引先一覧取得
		List<DepartmentEntity> entities = this.departmentRepository.findAll(spec, sort);
		
		// 値格納用のリスト作成
		List<DepartmentModel> departments =  new ArrayList<>();
		
		for(DepartmentEntity entity: entities) {		
			var department = new DepartmentModel();
			//
			department.setDepartmentSeq(entity.getDepartmentSeq());
			// 取引先SEQ
			department.setCompanySeq(entity.getCompanySeq());
			// 取引先名
			department.setDepartmentName(entity.getName());
			// リストに追加
			departments.add(department);
			
		}
	
		var vo = new GetDepartmentsVo();
		// 取引先リストの設定
		vo.setDepartment(departments);
		
		return vo;
	}
	
	
	/**
	 * 商品詳細画面のレスポンス
	 * @param vo
	 */
	@Transactional
	public GetProductVo getProductVo(Long productSeq){
		Optional<ProductEntity> product = productRepository.findById(productSeq);
		if(product.isEmpty()) {
			throw new AppException(String.format("該当の商品を取得できませんでした。 productsSeq: %s", productSeq));
		}
		
		return GetProductVo.mapTo(product.get());
	
	}
	
	/**
	 * 商品一覧取得
	 * @param vo
	 */
	@Transactional
	public GetProductsTableVo getProductsVo(GetProductConditionsVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<ProductEntity> spec = Specification.where(
				ProductSpec.productSeqEquals(condition.getProductSeq()))
				.and(ProductSpec.productNameEquals(condition.getProductName()))
				.and(ProductSpec.unitPriceFrom(condition.getUnitPriceFrom()))
				.and(ProductSpec.unitPriceTo(condition.getUnitPriceTo()))
				.and(ProductSpec.purchaseUnitPriceFrom(condition.getPurchaseUnitPriceFrom()))
				.and(ProductSpec.purchaseUnitPriceTo(condition.getPurchaseUnitPriceTo()))
				.and(ProductSpec.taxTypeEquals(condition.getTaxType()));
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "productSeq");
		
		Page<ProductEntity> pages = this.productRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		List<ProductTableModel> products = pages.get().map(e -> {
			var product = new ProductTableModel();
			// 商品seq
			product.setProductSeq(e.getProductSeq());
			// 商品名
			product.setProductName(e.getName());
			// 仕入れ料金
			product.setPurchaseUnitPrice(e.getPurchaseUnitPrice());
			// 税区分
			product.setTaxType(e.getTaxType().getDisplayName());
			// 定価
			product.setUnitPrice(e.getUnitPrice());
			return product;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetProductsTableVo();
		// トータルぺ―ジ
		vo.setTotalItemsNum(pages.getTotalElements());
		// 取引先リストの設定
		vo.setProduct(products);
		
		return vo;
	}
	
	/*
	 * 商品一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetProductsVo pullDownProduct() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "productSeq");
		
		// 商品一覧取得
		List<ProductEntity> entities = this.productRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<ProductModel> products =  new ArrayList<>();
		
		for(ProductEntity entity: entities) {		
			var product = new ProductModel();
			// 商品seq
			product.setProductSeq(entity.getProductSeq());
			// 商品名
			product.setProductName(entity.getName());
			// 仕入れ料金
			product.setPurchaseUnitPrice(entity.getPurchaseUnitPrice());
			// 税区分
			product.setTaxType(entity.getTaxType());
			// 定価
			product.setUnitPrice(entity.getUnitPrice());
			
			products.add(product);
			
		}
	
		var vo = new GetProductsVo();
		// 商品リストの設定
		vo.setProduct(products);
		
		return vo;
	}
	
	/**
	 * 商品更新処理
	 * @param vo
	 */
	@Transactional
	public void updateProducts(UpdateProductVo vo) {
		// 商品を取得
		var product= this.productRepository.findById(vo.getProduct().getProductSeq());
		
		// 対象の商品が取得できない場合はエラー
		if(product.isEmpty()) {
			throw new AppException(String.format("該当の商品を取得できませんでした。 productSeq: %s", vo.getProduct().getProductSeq()));			
		}
		
		var productEntity = product.get();
		productEntity.update(vo);
		this.productRepository.save(productEntity);
	}
	
	/**
	 * 倉庫詳細画面のレスポンス
	 * @param vo
	 */
	@Transactional
	public GetWarehouseVo getWarehouseVo(Long warehouseSeq){
		Optional<WarehouseEntity> warehouse = warehouseRepository.findById(warehouseSeq);
		if(warehouse.isEmpty()) {
			throw new AppException(String.format("該当の倉庫を取得できませんでした。 warehouseSeq: %s", warehouseSeq));
		}
		
		return GetWarehouseVo.mapTo(warehouse.get());
	
	}
	
	
	/**
	 * 倉庫一覧取得
	 * @param vo
	 */
	@Transactional
	public GetWarehousesVo getWarehouseVo(GetWarehouseConditionsVo condition) {
		// nullの場合は1ページ目として取得する
		if(condition.getPageNo() == null) {
			condition.setPageNo(0);
		}
		
		// 検索条件の設定
		Specification<WarehouseEntity> spec = Specification.where(
				WarehouseSpec.warehouseSeqEquals(condition.getWarehouseSeq()))
				.and(WarehouseSpec.warehouseNameEquals(condition.getWarehouseName()));
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "warehouseSeq");
		
		Page<WarehouseEntity> pages = this.warehouseRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		List<WarehouseModel> warehouses = pages.get().map(e -> {
			var warehouse = new WarehouseModel();
			// 倉庫seq
			warehouse.setWarehouseSeq(e.getWarehouseSeq());
			// 倉庫名
			warehouse.setWarehouseName(e.getName());
			return warehouse;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetWarehousesVo();
		// トータルぺ―ジ
		vo.setTotalItemsNum(pages.getTotalElements());
		// 取引先リストの設定
		vo.setWarehouse(warehouses);
		
		return vo;
	}
	
	/*
	 * 倉庫一覧のプルダウン
	 * @params vo
	 */
	@Transactional
	public GetWarehousesVo pullDownWarehouse() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "warehouseSeq");
		
		// 取引先一覧取得
		List<WarehouseEntity> entities = this.warehouseRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<WarehouseModel> warehouses =  new ArrayList<>();
		
		for(WarehouseEntity entity: entities) {		
			var warehouse = new WarehouseModel();
			// 倉庫seq
			warehouse.setWarehouseSeq(entity.getWarehouseSeq());
			// 倉庫名
			warehouse.setWarehouseName(entity.getName());
			
			warehouses.add(warehouse);
			
		}
	
		var vo = new GetWarehousesVo();
		// 取引先リストの設定
		vo.setWarehouse(warehouses);
		
		return vo;
	}
	
	/**
	 * 倉庫更新処理
	 * @param vo
	 */
	@Transactional
	public void updateWarehouse(UpdateWarehouseVo vo) {
		// 倉庫を取得
		var warehouse= this.warehouseRepository.findById(vo.getWarehouse().getWarehouseSeq());
		
		// 対象の倉庫が取得できない場合はエラー
		if(warehouse.isEmpty()) {
			throw new AppException(String.format("該当の倉庫を取得できませんでした。 warehouseSeq: %s", vo.getWarehouse().getWarehouseSeq()));			
		}
		
		var warehouseEntity = warehouse.get();
		warehouseEntity.update(vo);
		this.warehouseRepository.save(warehouseEntity);
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
		List<SupplierEntity> entities = this.supplierRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<SupplierModel> suppliers =  new ArrayList<>();
		
		for(SupplierEntity entity: entities) {		
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
			
}

