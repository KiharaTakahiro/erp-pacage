package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

@Data
public class GetSupplierProductConditionVo {
	// ページ番号
	private Integer pageNo;
	// 仕入商品Seq
	private Long supplierProductSeq;
	// 仕入商品名
	private String supplierProductName;
	// 仕入れ料金From
	private Long purchaseUnitPriceFrom;
	// 仕入れ料金To
	private Long purchaseUnitPriceTo;
}
