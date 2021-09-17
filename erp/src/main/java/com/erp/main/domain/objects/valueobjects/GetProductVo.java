package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.model.ProductModel;

import lombok.Data;

/*
 * 商品取得用のVO
 * @author 高山
 */
@Data
public class GetProductVo {
	
	// 商品
	private ProductModel product;
	
	/**
	 * エンティティからVoへのマッピング
	 * @param entity
	 * @return
	 */
	public static GetProductVo mapTo(ProductEntity entity) {

		var model = new ProductModel();
		// 商品SEQ
		model.setProductSeq(entity.getProductSeq());
		// 商品名
		model.setProductName(entity.getName());
		// 税区分
		model.setTaxType(entity.getTaxType());
		// 単価
		model.setUnitPrice(entity.getUnitPrice());
		// 仕入単価
		model.setPurchaseUnitPrice(entity.getPurchaseUnitPrice());
		
		// Voのセット
		var vo = new GetProductVo();
		vo.setProduct(model);
				
		return vo;
		
	}
}