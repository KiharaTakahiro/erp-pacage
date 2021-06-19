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
 * 在庫
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class StockEntityFileds extends BaseEntityFields {

	/**
	 * 在庫SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "stock_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "STOCK_SEQ")
	private Long stockSeq;

	/**
	 * 倉庫SEQ
	 */
	@NotNull
	@Column(name = "WAREHOUSE_SEQ")
	private Long warehouseSeq;

	/**
	 * 商品SEQ
	 */
	@NotNull
	@Column(name = "PRODUCT_SEQ")
	private Long productSeq;
	
	/**
	 * ロットSEQ
	 */
	@NotNull
	@Column(name = "LOT_SEQ")
	private Long lotSeq;
	
	/**
	 * 在庫数
	 */
	@Column(name = "STOCK_NUM")
	private Long stockNum;

}
