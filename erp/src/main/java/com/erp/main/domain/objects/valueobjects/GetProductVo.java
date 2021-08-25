package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.ProductEntity;
import com.erp.main.domain.objects.model.ProductModel;

import lombok.Data;

/*
 * 取引先取得用のVO
 * @author ngt
 */
@Data
public class GetProductVo {
	private ProductModel product;
	
	public static GetProductVo mapTo(ProductEntity entity) {
		var vo = new GetProductVo();
		var model = new ProductModel();
		model.setProductSeq(entity.getProductSeq());
		model.setProductName(entity.getName());
		model.setPurchaseUnitPrice(entity.getPurchaseUnitPrice());
		model.setUnitPrice(entity.getUnitPrice());
		model.setTaxType(entity.getTaxType());
		vo.setProduct(model);
		
		return vo;
	}

}
