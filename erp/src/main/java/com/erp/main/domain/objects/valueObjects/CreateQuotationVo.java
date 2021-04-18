package com.erp.main.domain.objects.valueObjects;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.main.domain.common.exception.AppException;
import com.erp.main.domain.component.MoneyComponent;
import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.entity.QuotationDetailEntity;
import com.erp.main.domain.objects.entity.QuotationEntity;
import com.erp.main.domain.repository.ProductRepository;

import lombok.Getter;
import lombok.Setter;

/**
 * 見積作成用のVo
 * @author takah
 *
 */
@Component
public class CreateQuotationVo extends QuotationEntity{
	
	/**
	 * エンティティ生成用クラス
	 * @return
	 */
	public QuotationEntity create() {
		QuotationEntity entity = new QuotationEntity();
		// 取引先SEQ
		entity.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		entity.setCompanySeq(this.getCompanySeq());
		// 見積番号
		entity.setQuotationNo(this.getQuotationNo());
		// 作成日
		entity.setCreateDate(this.getCreateDate());
		// 件名
		entity.setSubject(this.getSubject());
		// 値引合計
		entity.setDiscountTotal(this.getDiscountTotal());
		// 小計
		entity.setSubTotal(this.computeSubTotal());
		// 消費税
		entity.setTax(this.computeTax());
		// 合計金額
		entity.setTotal(this.computeTotal());
		return entity;
	}
	
	/**
	 * 金額計算コンポーネント
	 */
	@Autowired
	private MoneyComponent maneyComponent;
	
	/**
	 * 見積詳細のVo
	 */
	@Getter
	@Setter
	private List<CreateQuotationDetailVo> details;
	
	/**
	 * 小計の計算
	 * @return
	 */
	public Long computeSubTotal() {
		Long subTotal = 0L;
		for(CreateQuotationDetailVo detail: this.getDetails()) {
			subTotal += detail.computePrice();
		}
		return subTotal - this.getDiscountTotal();
		
	}
	
	/**
	 * 消費税の計算を行う
	 * @return
	 */
	public Long computeTax() {
		return maneyComponent.computeTax(this.computeSubTotal());
	}
	
	/**
	 * 合計金額の計算を行う
	 * @return
	 */
	public Long computeTotal() {
		return this.computeSubTotal() - this.computeTax();
	}
	
	/**
	 * 見積詳細部分のVO
	 * @author takah
	 *
	 */
	@Component
	public class CreateQuotationDetailVo extends QuotationDetailEntity {
		
		@Autowired
		private ProductRepository productRepository;
		
		/**
		 * 見積詳細作成
		 * @return
		 */
		public QuotationDetailEntity create(Long quotationSeq) {
			QuotationDetailEntity entity = new QuotationDetailEntity();
			// 見積SEQ
			entity.setQuotationSeq(quotationSeq);
			// 商品SEQ
			entity.setProductSeq(this.getProductSeq());
			// 数量
			entity.setQuantity(this.getQuantity());
			// 値引
			entity.setDiscount(this.getDiscount());
			return entity;
		}
		
		/**
		 * 金額計算処理
		 * @return
		 */
		public Long computePrice() {
			Optional<ProductEntity> product = this.productRepository.findById(this.getProductSeq());
			if(product.isEmpty()) {
				throw new AppException(String.format("存在しない商品です。productSeq: %d", this.getProductSeq()));
			}
			return product.get().getUnitPrice() * this.getQuantity();
		}
	}
}
