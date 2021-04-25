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
 * 仕入詳細
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class PurchasingDetailEntityFileds extends BaseEntityFields {

	/**
	 * 仕入詳細SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "purchasing_detail_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "PURCHASING_DETAIL_SEQ")
	private Long purchasingDetailSeq;

	/**
	 * 仕入SEQ
	 */
	@NotNull
	@Column(name = "PURCHASING_SEQ")
	private Long purchasingSeq;
	
	/**
	 * 商品SEQ
	 */
	@Column(name = "PRODUCT_SEQ")
	private Long productSeq;

	/**
	 * 仕入商品名
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * ロット番号
	 */
	@Column(name = "LOT_NO")
	private String lotNo;
	
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
	
}
