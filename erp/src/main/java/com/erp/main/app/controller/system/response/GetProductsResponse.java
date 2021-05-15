package com.erp.main.app.controller.system.response;

import java.util.List;

import lombok.Data;

/**
 * 商品取得API
 * @author 木原
 *
 */
public class GetProductsResponse extends BaseResponse{

	// 商品リスト
	List<Product> products;
	
	/**
	 * 商品情報のインナークラス
	 * @author takah
	 *
	 */
	@Data
	public static class  Product{

		// 商品名
		private String name;
		
		// 販売単価
		private Long unitPrice;
		
		// 仕入れ単価
		private Long purchaseUnitPrice;
	}
}