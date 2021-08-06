package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.common.enums.TaxType;

import lombok.Data;

/**
 * 仕入れ商品作成Vo
 * @author 高山
 *
 */

@Data
public class CreateSupplierProductVo {
	/**
	 * 仕入先Seq
	 */
	private Long supplierSeq;
	
	/**
	 *  仕入商品名
	 */
	private String supplierProductName;
	
	/**
	 *  仕入単価
	 */
	private Long purchaseUnitPrice;
	
	/**
	 *  税区分
	 */
	private TaxType taxType;
}
