package com.erp.main.domain.services;

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
import com.erp.main.domain.objects.valueobjects.GetClientsConditionsVo;
import com.erp.main.domain.objects.valueobjects.GetClientsVo;
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
		var tmp = this.supplierProductsRepository.save(entity);
		
		
//		var relationVo = new SupplierProductRelationVo();
//		relationVo.setSupplierSeq(vo.getSupplierSeq());
//		relationVo.setSupplierProductSeq(null);
//
//   	SupplierProductRelationEntity relationEntity = SupplierProductRelationEntity 
		
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
		Optional<CompanyEntity> product = this.companyRepository.findById(vo.getDepartmentCompanySeq());
		if(product.isEmpty()) {
			throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",vo.getDepartmentCompanySeq()));
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
		// トータルぺ―ジ
		vo.setMaxpage(pages.getTotalPages());
		// 取引先リストの設定
		vo.setClients(clients);
		
		return vo;
	}
	
}

