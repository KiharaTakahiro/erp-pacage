package com.erp.main.domain.objects.model;

import com.erp.main.domain.common.enums.TaxType;

import lombok.Data;

/**
 * 仕入商品のモデル
 * @author takah
 *
 */
@Data
public class SupplierProductModel {
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	// 仕入商品名
	private String supplierProductName;
	
	// 仕入価格
	private Long purchaseUnitPrice;
	
	// 税区分
	private TaxType taxType;
}
