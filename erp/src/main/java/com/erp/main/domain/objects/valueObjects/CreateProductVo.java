package com.erp.main.domain.objects.valueObjects;

import com.erp.main.domain.common.enums.TaxType;

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
	
	/**
	 * 仕入れ単価
	 */
	private Long purchaseUnitPrice;
	
	/**
	 * 税区分
	 */
	private TaxType taxType;

}
