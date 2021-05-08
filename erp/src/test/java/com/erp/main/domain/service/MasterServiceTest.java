package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.valueObjects.CreateClientsVo;
import com.erp.main.domain.objects.valueObjects.CreateProductVo;
import com.erp.main.domain.objects.valueObjects.CreateSupplierVo;
import com.erp.main.domain.repository.ClientsRepository;
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
}
