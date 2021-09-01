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
import com.erp.main.domain.objects.valueobjects.GetProductsVo;
import com.erp.main.domain.objects.valueobjects.SupplierProductRelationVo;
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
import com.erp.main.domain.specification.ClientsSpec;
import com.erp.main.domain.specification.DepartmentSpec;
import com.erp.main.domain.specification.ProductSpec;

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
	public GetClientsVo pullDownClients() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "clientsSeq");
		
		// 取引先一覧取得
		List<ClientsEntity> entitys = this.clientsRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<ClientModel> clients =  new ArrayList<>();
		
		for(ClientsEntity entity: entitys) {		
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
	public GetCompanysVo pullDownCompany() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "companySeq");
		
		// 取引先一覧取得
		List<CompanyEntity> entitys = this.companyRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<CompanyModel> companys =  new ArrayList<>();
		
		for(CompanyEntity entity: entitys) {		
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
	public GetDepartmentsVo pullDownDepartment(GetDepartmentConditionsVo condition) {
		// 検索条件の設定
		Specification<DepartmentEntity> spec = Specification.where(
			DepartmentSpec.departmentSeqEquals(condition.getDepartmentSeq()))
				.and(DepartmentSpec.companySeqEquals(condition.getCompanySeq()))
				.and(DepartmentSpec.departmentNameEquals(condition.getDepartmentName()));
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "departmentSeq");
		
		// 取引先一覧取得
		List<DepartmentEntity> entitys = this.departmentRepository.findAll(spec, sort);
		
		// 値格納用のリスト作成
		List<DepartmentModel> departments =  new ArrayList<>();
		
		for(DepartmentEntity entity: entitys) {		
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
			throw new AppException(String.format("該当の取引先を取得できませんでした。 productsSeq: %s", productSeq));
		}
		
		return GetProductVo.mapTo(product.get());
	
	}
	
	/**
	 * 商品一覧取得
	 * @param vo
	 */
	public GetProductsVo getProductsVo(GetProductConditionsVo condition) {
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
				.and(ProductSpec.purchaseUnitPriceTo(condition.getPurchaseUnitPriceTo()));
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "productSeq");
		
		Page<ProductEntity> pages = this.productRepository.findAll(spec, PageRequest.of(condition.getPageNo(), 15, sort));
		List<ProductModel> products = pages.get().map(e -> {
			var product = new ProductModel();
			// 商品seq
			product.setProductSeq(e.getProductSeq());
			// 商品名
			product.setProductName(e.getName());
			// 仕入れ料金
			product.setPurchaseUnitPrice(e.getPurchaseUnitPrice());
			// 税区分
			product.setTaxType(e.getTaxType());
			// 定価
			product.setUnitPrice(e.getUnitPrice());
			return product;
		}).collect(Collectors.toList());
		
		// 返却用のVo生成
		var vo = new GetProductsVo();
		// トータルぺ―ジ
		vo.setMaxpage(pages.getTotalPages());
		// 取引先リストの設定
		vo.setProduct(products);
		
		return vo;
	}
	/*
	 * 商品一覧のプルダウン
	 * @params vo
	 */
	public GetProductsVo pullDownProduct() {
		
		// ソートの設定
		var sort = Sort.by(Sort.Direction.ASC, "productSeq");
		
		// 取引先一覧取得
		List<ProductEntity> entitys = this.productRepository.findAll(sort);
		
		// 値格納用のリスト作成
		List<ProductModel> products =  new ArrayList<>();
		
		for(ProductEntity entity: entitys) {		
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
		// 取引先リストの設定
		vo.setProduct(products);
		
		return vo;
	}
			
}

