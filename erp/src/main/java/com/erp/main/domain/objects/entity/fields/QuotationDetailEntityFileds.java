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
 * 見積詳細を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class QuotationDetailEntityFileds extends BaseEntityFields {

	/**
	 * 見積詳細SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "quotation_detail_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "QUOTATION_DETAIL_SEQ")
	private Long quotationDetailSeq;

	/**
	 * 見積SEQ
	 */
	@NotNull
	@Column(name = "QUOTATION_SEQ")
	private Long quotationSeq;
	
	/**
	 * 商品SEQ
	 */
	@Column(name = "PRODUCT_SEQ")
	private Long productSeq;
	
	/**
	 * 数量
	 */
	@NotNull
	@Column(name="QUANTITY")
	private Integer quantity;
	
	/**
	 * 値引
	 */
	@NotNull
	@Column(name="DISCOUNT")
	private Long discount;

	/**
	 * 金額
	 */
	@NotNull
	@Column(name="PRICE")
	private Long price;
	
}
