package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/*
 * 仕入先取得用の検索条件
 */

@Data
public class GetSupplierProductsConditionVo {
	// ページ番号
	private Integer pageNo;
	// 取引先Seq
	private Long supplierProductSeq;
	// 取引先名
	private String supplierProductName;

}
