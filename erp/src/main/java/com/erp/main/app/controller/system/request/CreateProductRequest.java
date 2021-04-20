package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueObjects.CreateProductVo;
import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 商品マスタ作成APIのリクエスト
 * @author 木原
 *
 */
@Data
public class CreateProductRequest {
	
	/**
	 * 商品名
	 */
	@NotNull
	private String productName;
	
	/**
	 * 単価
	 */
	@NotNull
	private Long unitPrice;
	
	public CreateProductVo mapTo() {
		CreateProductVo vo = new CreateProductVo();
		// 商品名
		vo.setProductName(this.getProductName());
		// 金額
		vo.setUnitPrice(this.getUnitPrice());
		return vo;
	}
	
}
