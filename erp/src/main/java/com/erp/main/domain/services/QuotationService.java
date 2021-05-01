package com.erp.main.domain.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo.CreateQuotationDetailVo;
import com.erp.main.domain.repository.ProductRepository;
import com.erp.main.domain.repository.QuotationRepository;

/**
 * 見積のサービス
 * @author 木原
 *
 */
@Service
public class QuotationService {
	
	/**
	 * 見積リポジトリ
	 */
	@Autowired
	private QuotationRepository quotationRepository;

	/**
	 * 商品リポジトリ
	 */
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 金額コンポーネント
	 */
	@Autowired
	private MoneyComponent moneyComponent;
	
	/**
	 * 見積詳細作成
	 * @param createQuotationVo
	 */
	@Transactional
	public void createQuotation(CreateQuotationVo createQuotationVo) {
		
		// 見積詳細の作成
		Set<QuotationDetailEntity> detailEntities = new HashSet<>();
		// 小計
		long subtotal = 0L;
		
		// 見積詳細作成処理
		for(CreateQuotationDetailVo detailVo: createQuotationVo.getDetails()) {
			
			// 商品の取得
			Optional<ProductEntity> product = this.productRepository.findById(detailVo.getProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("対象の商品が取得できません。productSeq: %s",detailVo.getProductSeq()));
			}
			
			// 見積詳細用のエンティティ生成
			QuotationDetailEntity detailEntity = QuotationDetailEntity.create(detailVo);
			
			// 金額 (単金 × 数量 - 値引)
			long price = product.get().getUnitPrice() * detailVo.getQuantity() - detailVo.getDiscount();
			detailEntity.setPrice(price);

			// 小計を加算する
			subtotal += price;
			
			// 見積詳細の追加
			detailEntities.add(detailEntity);
			
		}
		
		// VOからマッピング可能な部分をマッピング
		QuotationEntity quotation = QuotationEntity.create(createQuotationVo);
		
		// 小計
		quotation.setSubTotal(subtotal);
		// 消費税
		long tax = this.moneyComponent.computeTax(subtotal);
		quotation.setTax(tax);
		// 合計(小計 + 消費税)
		quotation.setTotal(subtotal + tax);
		
		// 見積詳細をセットする
		quotation.setQuotationDetail(detailEntities);
		
		// 見積の保存
		quotation = this.quotationRepository.save(quotation);
	}
}
