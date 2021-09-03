package com.erp.main.app.controller.system.request;

import com.erp.main.domain.common.enums.TaxType;
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
    // 仕入料金From
	private Long purchaseUnitPriceFrom;
    // 仕入料金To
	private Long purchaseUnitPriceTo;
    // 定価
	private Long unitPrice;
	// 税区分
	private TaxType taxType;
	
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
        // 仕入料金
//    	vo.setPurchaseUnitPrice(this.getPurchaseUnitPrice());
        // 仕入料金From
    	vo.setPurchaseUnitPriceFrom(this.getPurchaseUnitPrice());
        // 仕入料金To
    	vo.setPurchaseUnitPriceTo(this.getPurchaseUnitPrice());
        // 定価
//    	vo.setUnitPrice(this.getPurchaseUnitPrice());
    	// 税区分
    	vo.setTaxType(this.getTaxType());
        
        
        return vo;
    }
}
