package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.RecivedOrderModel;

import lombok.Data;

/*
 * 受注詳細取得用VO
 * @author ngt
 */
@Data
public class GetRecivedOrderVo {
	//受注票モデル
	private RecivedOrderModel recivedOrder;
	
	private List<GetRecivedOrderDetailVo> details;
	
	@Data
	public static class GetRecivedOrderDetailVo {
		//
		private Long recicedOrderDetailSeq;
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
}
