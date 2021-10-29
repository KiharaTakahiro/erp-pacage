package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 仕入先関連のモデル
 * @author 高山
 *
 */
@Data
public class SupplierRelationModel {
	// 仕入先Seq
	private Long supplierSeq;
	
	// 仕入商品Seq
	private String supplierProductSeq;

}
