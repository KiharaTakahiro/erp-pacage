package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * 発注
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class OrderEntityFileds extends BaseEntityFields {

	/**
	 * 発注SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "order_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "ORDER_SEQ")
	private Long orderSeq;

	/**
	 * 仕入先SEQ
	 */
	@Column(name = "SUPPLIER_SEQ")
	private Long supplierSeq;
	
	/**
	 * 発注金額
	 */
	@Column(name = "TOTAL")
	private Long total;

	/**
	 * 消費税
	 */
	@Column(name = "TAX")
	private Long tax;

}
