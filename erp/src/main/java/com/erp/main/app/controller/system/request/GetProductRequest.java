package com.erp.main.app.controller.system.request;

import lombok.Data;

/*
 * 商品取得のリクエスト
 * @author 高山
 */
@Data
public class GetProductRequest {
	// ページNo
	private Integer pageNo;
    // 取引先SEQ
    private Long productSeq;
    
	// 商品名
	private String productName;
	// 単価
	private Long unitPrice;
	// 仕入れ単価
	private Long purchaseUnitPrice;
	// 税区分
	private Integer taxType;
}
