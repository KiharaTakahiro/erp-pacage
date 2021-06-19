package com.erp.main.app.controller.system.request;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 仕入れ先登録
 * @author takah
 *
 */
@Data
public class CreateSupplierRequest {
	
	// 仕入れ先名
	@NotNull
	private String supplierName;
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateSupplierVo mapTo() {
		var vo = new CreateSupplierVo();
		
		// 仕入れ先名
		vo.setSupplierName(this.getSupplierName());

		return vo;
	}
}
