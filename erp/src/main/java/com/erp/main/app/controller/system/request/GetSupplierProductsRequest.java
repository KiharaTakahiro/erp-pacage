package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetSupplierProductsConditionVo;

import lombok.Data;

/** 仕入商品のリクエスト
 * @author 高山
 */
@Data
public class GetSupplierProductsRequest {
	// page no.
	private Integer pageNo;
	// 商品名seq
	private Long SupplierProductSeq;
	// 商品名
	private String SupplierProductName;
	
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return vo
     */
	
	public GetSupplierProductsConditionVo mapTo() {
		var vo = new GetSupplierProductsConditionVo();
		// page no.
		vo.setPageNo(this.getPageNo());
		// 商品名seq
		vo.setSupplierProductsSeq(this.getSupplierProductSeq());
		// 商品名
		vo.setSupplierProductsName(this.getSupplierProductName());
		return vo;
	}
}
