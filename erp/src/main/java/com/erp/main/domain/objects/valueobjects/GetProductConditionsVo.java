package com.erp.main.domain.objects.valueobjects;

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
	// 定価from
	private Long unitPriceFrom;
	// 定価to
	private Long unitPriceTo;
	// 仕入料金from
	private Long purchaseUnitPriceFrom;
	// 仕入料金to
	private Long purchaseUnitPriceTo;
	// 税区分
	// HACK: ここでEnumで受けることもできる気がしますが、一旦これで進める
	private Integer taxType;
	

}
