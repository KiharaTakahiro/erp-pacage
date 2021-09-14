package com.erp.main.domain.objects.model;

import lombok.Data;

/*
 * 受注詳細モデル
 * @author ngt
 */
@Data
public class RecivedOrderDetailModel {
	//受注詳細Seq
	private Long recicedOrderDetailSeq;
	
	//受注Seq
	private Long recivedOrderSeq;
	
	// 配送日
	private String deriveryDate;
	
	// 値引
	private Long discount;
	
	// ロットSEQ
	private Long lotSeq;
	
	// 商品SEQ
	private Long productSeq;
	
	// 数量
	private Integer quantity;
	
	// 状態
	private Integer status;
}	