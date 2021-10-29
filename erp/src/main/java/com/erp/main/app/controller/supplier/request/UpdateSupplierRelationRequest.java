package com.erp.main.app.controller.supplier.request;

import com.erp.main.domain.objects.valueobjects.SupplierProductRelationVo;

import lombok.Data;


/*
 * 仕入先紐づけのリクエスト
 * @author 高山
 */
@Data
public class UpdateSupplierRelationRequest {
	// 仕入先Seq
	private Long supplierSeq;
	
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	public SupplierProductRelationVo mapTo() {
		var vo = new SupplierProductRelationVo();
		vo.setSupplierSeq(this.getSupplierSeq());
		vo.setSupplierProductSeq(this.getSupplierProductSeq());
			
		return vo;
	}


}
