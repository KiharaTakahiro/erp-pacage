package com.erp.main.domain.objects.model;

import lombok.Data;


/**
 * 商品のモデル
 * @author 高山
 *
 */
@Data
public class ProductTableModel {
	// 商品Seq
	private Long productSeq;
	
	// 商品名
	private String productName;
	
	// 単価
	private Long unitPrice;
	
	// 仕入れ単価
	private Long purchaseUnitPrice;
	
	// 税区分
	private String taxType;
}

