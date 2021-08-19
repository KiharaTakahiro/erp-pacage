package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 取引先のモデル
 * @author takah
 *
 */
@Data
public class SupplierProductModel {
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	// 仕入商品名
	private String supplierProductName;
}
