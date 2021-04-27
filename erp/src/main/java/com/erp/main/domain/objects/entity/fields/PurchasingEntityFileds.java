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
 * 仕入
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class PurchasingEntityFileds extends BaseEntityFields {

	/**
	 * 仕入れSEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "purchasing_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "PURCHASING_SEQ")
	private Long purchasingSeq;
	
	/**
	 * 発注SEQ
	 */
	@Column(name = "ORDER_SEQ")
	private Long orderSeq;

	/**
	 * 仕入先SEQ
	 */
	@Column(name = "SUPPLIER_SEQ")
	private Long supplierSeq;

	/**
	 * 仕入日
	 */
	@Column(name = "PURCHASING_DATE")
	private String purchasingDate;
	
	/**
	 * 仕入金額合計
	 */
	@Column(name = "TOTAL")
	private Long total;

	/**
	 * 消費税
	 */
	@Column(name = "TAX")
	private Long tax;
	
	
}
