package com.erp.main.domain.objects.model;

import com.erp.main.domain.common.enums.TaxType;

import lombok.Data;

/**
 * 商品のモデル
 * @author ngt
 *
 */
@Data
public class ProductModel {
	// 商品Seq
	private Long productSeq;
	
	// 商品名
	private String productName;
	
	// 単価
	private Long unitPrice;
	
	// 仕入れ単価
	private Long purchaseUnitPrice;
	
	// 税区分
	// HACK: ここでEnumで受けることもできる気がしますが、一旦これで進める
	private TaxType taxType;
}
