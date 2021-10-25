package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 仕入商品のモデル
 * @author 高山
 *
 */
@Data
public class SupplierProductTableModel {
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	// 仕入商品名
	private String supplierProductName;
	
	// 仕入れ単価
	private Long purchaseUnitPrice;
	
	// 税区分
	private String taxType;
}
