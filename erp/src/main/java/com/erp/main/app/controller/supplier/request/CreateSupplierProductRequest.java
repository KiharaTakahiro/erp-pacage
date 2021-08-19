package com.erp.main.app.controller.supplier.request;

import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;

import lombok.Data;

/**
 * 仕入れ商品作成APIのリクエスト
 * @author takayama
 *
 */

@Data
public class CreateSupplierProductRequest {
	// 仕入先Seq
	private Long supplierSeq;
	// 仕入商品名
	private String supplierProductName;
	// 値段
	private Long purchaseUnitprice;
	// 消費税
	private TaxType taxType;
	/**
	 * リクエストからvoへのマッピング
	 * @return vo
	 */
	public CreateSupplierProductVo mapTo() {
		var vo = new CreateSupplierProductVo();
		// 仕入先Seq
		vo.setSupplierSeq(this.getSupplierSeq());
		// 仕入商品名
		vo.setSupplierProductName(this.getSupplierProductName());
		// 値段
		vo.setPurchaseUnitPrice(this.getPurchaseUnitprice());
		// 消費税
		vo.setTaxType(this.getTaxType());		
		
		return vo;
	}

}
