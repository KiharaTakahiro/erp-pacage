package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 仕入れ商品関連テーブルのvo
 * @author 高山
 */
@Data
public class SupplierProductRelationVo {

	/*
	 *  仕入先Seq
	 */
	private Long supplierSeq;
	
	/*
	 *  仕入商品Seq
	 */
	private Long supplierProductSeq;
	
}
