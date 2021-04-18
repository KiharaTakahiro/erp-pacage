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
 * 取引先を管理する
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class ProductEntityFileds extends BaseEntityFields {

	/**
	 * 商品SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "product_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "PRODUCT_SEQ")
	private Long productSeq;

	/**
	 * 商品名
	 */
	@NotNull
	@Column(name = "NAME")
	private Long name;
	
	/**
	 * 単価
	 */
	@NotNull
	@Column(name="UNIT_PRICE")
	private Long unitPrice;
	
}
