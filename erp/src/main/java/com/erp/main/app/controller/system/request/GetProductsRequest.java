package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetProductConditionsVo;

import lombok.Data;

/*
 * 商品取得のリクエスト
 * @author 高山
 */
@Data
public class GetProductsRequest {
	// ページNo
	private Integer pageNo;
    // 商品SEQ
    private Long productSeq;
    // 商品名
    private String productName;
    // 仕入料金
	private Long purchaseUnitPrice;
    // 原価From
	private Long purchaseUnitPriceFrom;
    // 原価To
	private Long purchaseUnitPriceTo;
    // 定価
	private Long unitPrice;
    // 単価From
	private Long unitPriceFrom;
    // 単価To
	private Long unitPriceTo;
	// 税区分
	private Integer taxType;
	
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetProductConditionsVo mapTo() {
        var vo = new GetProductConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 商品SEQ
        vo.setProductSeq(this.getProductSeq());
        // 商品名
        vo.setProductName(this.getProductName());
        // 原価From
    	vo.setPurchaseUnitPriceFrom(this.getPurchaseUnitPrice());
        // 原価To
    	vo.setPurchaseUnitPriceTo(this.getPurchaseUnitPrice());
    	// 単価From
    	vo.setUnitPriceFrom(this.getUnitPrice());
        // 単価To
    	vo.setUnitPriceTo(this.getUnitPrice());
    	// 税区分
    	vo.setTaxType(this.getTaxType());
        
        
        return vo;
    }
}
