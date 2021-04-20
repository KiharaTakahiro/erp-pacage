package com.erp.main.domain.objects.valueObjects;

import lombok.Data;

/**
 * 商品作成Vo
 * @author 木原
 *
 */
@Data
public class CreateProductVo {

	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * 単価
	 */
	private Long unitPrice;

}
