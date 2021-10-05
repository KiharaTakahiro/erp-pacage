package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 仕入先のモデル
 * @author 高山
 *
 */
@Data
public class SupplierModel {
	// 仕入先Seq
	private Long supplierSeq;
	
	// 仕入先名
	private String supplierName;

}
