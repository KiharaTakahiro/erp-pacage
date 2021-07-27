package com.erp.main.domain.objects.entity.fields;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.erp.main.domain.common.enums.OrderStatus;
import com.erp.main.domain.common.enums.OrderStatus.OrderStatusConverter;

import lombok.Getter;
import lombok.Setter;

/**
 * 発注詳細
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class OrderDetailEntityFileds extends BaseEntityFields {

	/**
	 * 発注詳細SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "order_detail_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "ORDER_DETAIL_SEQ")
	private Long orderDetailSeq;
	
	/**
	 * 発注SEQ
	 */
	@Column(name = "ORDER_SEQ")
	private Long orderSeq;

	/**
	 * 発注ステータス
	 */
	@Column(name = "STATUS")
	@Convert(converter = OrderStatusConverter.class)
	@Enumerated(EnumType.ORDINAL)
	private OrderStatus status;


	/**
	 * 商品SEQ
	 */
	@Column(name = "SUPPLIER_PRODUCT_SEQ")
	private Long supplierProductSeq;
	
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
	 * 納期
	 */
	@NotNull
	@Column(name="DERIVERY_DATE")
	private String deriveryDate;
}
