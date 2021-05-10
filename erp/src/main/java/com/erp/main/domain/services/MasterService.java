package com.erp.main.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.CompanyEntity;
import com.erp.main.domain.objects.entity.DepartmentEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.valueObjects.CreateClientsVo;
import com.erp.main.domain.objects.valueObjects.CreateCompanyVo;
import com.erp.main.domain.objects.valueObjects.CreateDepartmentVo;
import com.erp.main.domain.objects.valueObjects.CreateProductVo;
import com.erp.main.domain.objects.valueObjects.CreateSupplierVo;
import com.erp.main.domain.repository.ClientsRepository;
import com.erp.main.domain.repository.CompanyRepository;
import com.erp.main.domain.repository.DepartmentRepository;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.SupplierRepository;

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
		Optional<DepartmentEntity> product = this.departmentRepository.findById(vo.getDepartmentCompanySeq());
		if(product.isEmpty()) {
			throw new AppException(String.format("対象の会社が取得できません。companySeq: %s",vo.getDepartmentCompanySeq()));
		}
		
		DepartmentEntity entity = DepartmentEntity.create(vo);
		this.departmentRepository.save(entity);
		
	}
}

