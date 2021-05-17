package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 受注
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class RecivedOrderEntityFileds extends BaseEntityFields {

	/**
	 * 受注SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "recived_order_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "RECIVED_ORDER_SEQ")
	private Long recivedOrderSeq;

	/**
	 * 見積SEQ
	 */
	@NotNull
	@Column(name = "QUOTATION_SEQ")
	private Long quotationSeq;
	
	/**
	 * 会社SEQ
	 */
	@Column(name = "COMPANY_SEQ")
	private Long companySeq;

	/**
	 * 部門SEQ
	 */
	@Column(name = "DEPARTMENT_SEQ")
	private Long departmentSeq;

	/**
	 * 受注日
	 */
	@NotNull
	@Column(name="RECIVED_ORDER_DATE")
	private String recivedOrderDate;
	
	/**
	 * 取引先SEQ
	 */
	@NotNull
	@Column(name="CLIENTS_SEQ")
	private Long clientsSeq;
	
	/**
	 * 受注金額合計
	 */
	@Column(name="TOTAL")
	private Long total;

	/**
	 * 消費税合計
	 */
	@Column(name="TAX")
	private Long tax;
	
}
