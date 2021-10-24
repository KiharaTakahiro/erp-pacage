package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.SupplierProductEntity;
import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;

/*
 * 仕入商品の取得VO
 * @author 高山
 */
@Data
public class GetSupplierProductVo {
	
	// 仕入商品
	private SupplierProductModel supplierProduct;
	
	/**
	 * エンティティからVoへのマッピング
	 * @param entity
	 * @return
	 */
	public static GetSupplierProductVo mapTo(SupplierProductEntity entity) {

		var model = new SupplierProductModel();
		// 仕入商品SEQ
		model.setSupplierProductSeq(entity.getSupplierProductSeq());
		// 仕入商品名
		model.setSupplierProductName(entity.getName());

		// Voのセット
		var vo = new GetSupplierProductVo();
		vo.setSupplierProduct(model);
				
		return vo;
		
	}
}
