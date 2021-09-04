package com.erp.main.app.controller.system.request;

import com.erp.main.domain.common.enums.BaseEnum;
import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.model.ProductModel;
import com.erp.main.domain.objects.valueobjects.UpdateProductVo;

import lombok.Data;

/*
 * 商品取得のリクエスト
 * @author 木原
 */
@Data
public class UpdateProductsRequest {
	
	// 商品Seq
	private Long productSeq;
	
	// 商品名
	private String productName;
	
	// 単価
	private Long unitPrice;
	
	// 仕入れ単価
	private Long purchaseUnitPrice;
	
	// 税区分
	private Integer taxType;
	
	/**
	 * Voへのマッピング
	 * @return
	 */
	public  UpdateProductVo mapTo() {
		
		var model = new ProductModel();
		// 商品SEQ
		model.setProductSeq(this.getProductSeq());
		// 商品名
		model.setProductName(this.getProductName());
		// 単価
		model.setUnitPrice(this.getUnitPrice());
		// 仕入れ単価
		model.setPurchaseUnitPrice(this.getPurchaseUnitPrice());
		// 税区分
		model.setTaxType(BaseEnum.of(TaxType.class, this.getTaxType()));
		
		// Voの生成
		var vo = new UpdateProductVo();
		vo.setProduct(model);
		
		return vo;

	}
	
}
