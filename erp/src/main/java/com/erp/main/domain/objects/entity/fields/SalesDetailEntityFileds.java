package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 売上詳細
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class SalesDetailEntityFileds extends BaseEntityFields {

	/**
	 * 売上詳細SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "sales_detail_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "SALES_DETAIL_SEQ")
	private Long salesDetailSeq;

	/**
	 * 売上SEQ
	 */
	@Column(name = "SALES_SEQ")
	private Long salesSeq;

	/**
	 * 金額
	 */
	@NotNull
	@Column(name="PRICE")
	private Long price;
	
	/**
	 * 消費税
	 */
	@Column(name="TAX")
	private Long tax;

	/**
	 * 請求日
	 */
	@Column(name="BILLING_DATE")
	private String billingDate;
}
