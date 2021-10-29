package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 仕入先取得用の検索条件
 * 
 */

@Data
public class GetSupplierConditionsVo {
	// ページ番号
	private Integer pageNo;
	// 仕入先Seq
	private Long supplierSeq;
	// 仕入先名
	private String supplierName;
	
	// 商品名seq
	private Long supplierProductSeq;
	// 商品名
	private String supplierProductName;
	// 仕入価格From
	private Long purchaseUnitPriceFrom;
	// 仕入価格To
	private Long purchaseUnitPriceTo;
	// 税区分
	private Integer taxType;

}
