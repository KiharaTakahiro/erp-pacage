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

import com.erp.main.domain.common.enums.RacivedOrderStatus;
import com.erp.main.domain.common.enums.RacivedOrderStatus.RacivedOrderStatusConverter;

import lombok.Getter;
import lombok.Setter;

/**
 * 受注詳細
 * 
 * @author 木原
 *
 */
@Setter
@Getter
@MappedSuperclass
public class RecivedOrderDetailEntityFileds extends BaseEntityFields {

	/**
	 * 受注詳細SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "recived_order_detail_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "RECIVED_ORDER_DETAIL_SEQ")
	private Long recivedOrderDetailSeq;

	/**
	 * 受注SEQ
	 */
	@Column(name = "RECIVED_ORDER_SEQ")
	private Long recivedOrderSeq;

	/**
	 * ロットSEQ
	 */
	@Column(name = "LOT_SEQ")
	private Long lotSeq;

	/**
	 * 商品SEQ
	 */
	@Column(name = "PRODUCT_SEQ")
	private Long productSeq;
	
	/**
	 * 数量
	 */
	@Column(name="QUANTITY")
	private Integer quantity;
	
	/**
	 * 値引
	 */
	@Column(name="DISCOUNT")
	private Long discount;
	
	/**
	 * 納期
	 */
	@Column(name="DERIVERY_DATE")
	private String deriveryDate;
	
	/**
	 * 受注ステータス
	 */
	@Column(name="STATUS")
	@Convert(converter = RacivedOrderStatusConverter.class)
	@Enumerated(EnumType.ORDINAL)
	private RacivedOrderStatus status;
	
}
