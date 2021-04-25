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
 * 売上
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class SalesEntityFileds extends BaseEntityFields {

	/**
	 * 売上SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "sales_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "SALES_SEQ")
	private Long salesSeq;
	
	/**
	 * 受注SEQ
	 */
	@Column(name = "RECIVED_ORDER_SEQ")
	private Long recivedOrderSeq;
	
	/**
	 * 仕入先SEQ
	 */
	@Column(name = "CLIENTS_SEQ")
	private Long clientsSeq;

	/**
	 * 売上日
	 */
	@Column(name = "SALES_DATE")
	private String salesDate;

	/**
	 * 売上金額合計
	 */
	@Column(name="TOTAL")
	private Long total;

	/**
	 * 消費税合計
	 */
	@Column(name="TAX")
	private Long tax;
}
