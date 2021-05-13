
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
	
	@Test
	public void registeDepartmentSuccessCase1() {
		
		// 実行用のデータ作成
		CreateDepartmentVo vo = new CreateDepartmentVo();
		// 取得処理をモック化
		Optional<CompanyEntity> departmentOpt = this.createDefaultCompanyData();
		Mockito.when(this.companyRepository.findById(1L)).thenReturn(departmentOpt);
		
		// 会社Seq
		vo.setDepartmentCompanySeq(1L);
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
		vo.setDepartmentCompanySeq(1L);
		// 部署名
		vo.setDepartmentName("test部");
		
		Assertions.assertThrows(AppException.class, () -> masterService.createDepartment(vo));
		

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
}
