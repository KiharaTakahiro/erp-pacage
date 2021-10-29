package com.erp.main.app.controller.supplier.request;

import com.erp.main.domain.objects.valueobjects.GetSupplierProductConditionVo;

import lombok.Data;

/**
 * 仕入先取得リクエスト
 * @author 高山
 *
 */

@Data
public class GetSupplierRelationRequest {
	// ページNo
	private Integer pageNo;
    // 仕入先SEQ
    private Long supplierSeq;
    // 仕入先名
    private String supplierName;
    
	// 商品名seq
	private Long supplierProductSeq;
	// 商品名
	private String supplierProductName;
	// 仕入価格From
	private Long purchaseUnitPriceFrom;
	// 仕入価格To
	private Long purchaseUnitPriceTo;
	// 税区分
	private Integer taxType;
	
    
    
    
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetSupplierProductConditionVo mapTo() {
        var vo = new GetSupplierProductConditionVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 仕入先SEQ
        vo.setSupplierSeq(this.getSupplierSeq());
        // 仕入先名
        vo.setSupplierName(this.getSupplierName());
        
		// 商品名seq
		vo.setSupplierProductSeq(this.getSupplierProductSeq());
		// 商品名
		vo.setSupplierProductName(this.getSupplierProductName());
		// 仕入価格From
		vo.setPurchaseUnitPriceFrom(this.getPurchaseUnitPriceFrom());
		// 仕入価格To
		vo.setPurchaseUnitPriceTo(this.getPurchaseUnitPriceTo());
    	// 税区分
    	vo.setTaxType(this.getTaxType());
        
        return vo;
    }

}
