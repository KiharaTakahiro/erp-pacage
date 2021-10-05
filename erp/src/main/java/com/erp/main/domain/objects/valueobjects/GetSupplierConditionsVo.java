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

}
