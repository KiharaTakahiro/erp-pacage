package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.common.enums.TaxType;

import lombok.Data;

/**
 * 商品取得用の検索条件
 * 
 */

@Data
public class GetProductConditionsVo {
	// ページ番号
	private Integer pageNo;
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
