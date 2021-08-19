package com.erp.main.domain.objects.entity.fields;

import java.io.Serializable;

import lombok.Data;


/**
 * 仕入れ先と仕入れ先商品の連関tableのkey
 * 
 * @author 
 *
 */
@Data
public class SupplierProductRelationKey implements Serializable {
	
	/**
	 * 仕入れ先SEQ
	 */
	private Long supplierSeq;

	/**
	 * 仕入れ先商品SEQ
	 */
	private Long supplierProductSeq;
}
