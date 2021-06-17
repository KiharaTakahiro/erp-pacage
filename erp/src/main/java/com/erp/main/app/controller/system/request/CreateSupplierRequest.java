package com.erp.main.app.controller.system.request;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CreateSupplierRequest {
	
	/**
	 * 取引先名
	 */
	@NotNull
	private String supplierName;
	
	public CreateSupplierVo mapTo() {
		CreateSupplierVo vo = new CreateSupplierVo();
		// 商品名
		vo.setSupplierName(this.getSupplierName());
		return vo;
	}
}
