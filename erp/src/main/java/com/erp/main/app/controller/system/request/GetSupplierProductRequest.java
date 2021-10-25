package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetSupplierProductConditionVo;

import lombok.Data;

/** 仕入商品のリクエスト
 * @author 高山
 */
@Data
public class GetSupplierProductRequest {
	// page no.
	private Integer pageNo;
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
     * @return vo
     */
	
	public GetSupplierProductConditionVo mapTo() {
		var vo = new GetSupplierProductConditionVo();
		// page no.
		vo.setPageNo(this.getPageNo());
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
