package com.erp.main.domain.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.valueObjects.CreateProductVo;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.services.MasterService;

/**
 * MasterServiceのパターン網羅用のテスト
 * @author takah
 *
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
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
}
