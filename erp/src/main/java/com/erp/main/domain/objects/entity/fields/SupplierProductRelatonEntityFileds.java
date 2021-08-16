package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SupplierProductRelatonEntityFileds {

	/**
	 * 仕入先SEQ
	 */
	@NotNull
	@Column(name = "SUPPLIER_SEQ")
	private Long supplierSeq;
	
	/**
	 * 仕入商品SEQ
	 */
	@NotNull
	@Column(name = "SUPPLIER_PRODUCT_SEQ")
	private Long supplierProductSeq;
}
