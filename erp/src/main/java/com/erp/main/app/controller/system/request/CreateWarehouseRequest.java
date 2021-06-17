package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateWarehouseVo;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CreateWarehouseRequest {
	/*
	 * 倉庫名
	 */
	@NotNull
	private String warehouseName;
	
	public CreateWarehouseVo mapTo() {
		 CreateWarehouseVo vo = new  CreateWarehouseVo();
		// 商品名
		vo.setWarehouseName(this.getWarehouseName());
		return vo;
	}
	

}
