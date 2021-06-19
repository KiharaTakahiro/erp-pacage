package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateWarehouseVo;
import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 倉庫登録リクエスト
 * @author takah
 *
 */
@Data
public class CreateWarehouseRequest {

	// 倉庫名
	@NotNull
	private String warehouseName;
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateWarehouseVo mapTo() {
		 
		var vo = new  CreateWarehouseVo();
		
		// 商品名
		vo.setWarehouseName(this.getWarehouseName());
		
		return vo;
	}
	

}
