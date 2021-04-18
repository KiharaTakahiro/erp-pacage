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
 * 見積を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class QuotationEntityFileds extends BaseEntityFields {

	/**
	 * 見積SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "quotation_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "QUOTATION_SEQ")
	private Long quotationSeq;

	/**
	 * 取引先SEQ
	 */
	@NotNull
	@Column(name="CLIENTS_SEQ")
	private Long clientsSeq;

	/**
	 * 会社SEQ
	 */
	@Column(name = "COMPANY_SEQ")
	private Long companySeq;
	
	/**
	 * 見積番号
	 */
	@NotNull
	@Column(name = "QUOTATION_NO")
	private String quotationNo;
	
	/**
	 * 作成日
	 */
	@NotNull
	@Column(name="CREATE_DATE")
	private String createDate;
		
	/**
	 * 件名
	 */
	@NotNull
	@Column(name="SUBJECT")
	private String subject;
	
	/**
	 * 値引合計
	 */
	@NotNull
	@Column(name="DISCOUNT_TOTAL")
	private Long discountTotal;
	
	/**
	 * 小計
	 */
	@NotNull
	@Column(name="SUBTOTAL")
	private Long subTotal;
	
	/**
	 * 消費税
	 */
	@NotNull
	@Column(name="TAX")
	private Long tax;
	
	/**
	 * 合計金額
	 */
	@NotNull
	@Column(name="TOTAL")
	private Long total;
	
}
