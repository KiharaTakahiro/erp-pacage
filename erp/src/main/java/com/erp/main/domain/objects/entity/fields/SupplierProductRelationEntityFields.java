package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * 仕入れ先と仕入れ先商品の連関table
 * 
 * @author NGT
 *
 */
@Setter
@Getter
@MappedSuperclass
public class SupplierProductRelationEntityFields extends BaseEntityFields {
	

	/**
	 * 仕入れ先SEQ
	 */
	@Id //PK
	@Column(name = "SUPPLIER_SEQ") 
	private Long supplierSeq;
	

	/**
	 * 仕入れ先商品SEQ
	 */
	@Id //PK
	@Column(name = "SUPPLIER_PRODUCT_SEQ") 
	private Long supplierProductSeq;

}
