package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.common.enums.OrderStatus;

import lombok.Data;

/**
 * 発注作成用のVo
 * @author 高山
 *
 */

@Data
public class CreateOrderVo {
	
	// 発注SEQ
	private Long orderSeq;
	
	// 仕入先SEQ
	private Long supplierSeq;
	
	// 発注金額
	private Long total;
	
	// 消費税
	private Long tax;
	
	private List<CreateOrderDetailVo> details;
	
	/**
	 * 発注詳細のリクエスト
	 * @author ngt
	 *
	 */
	@Data
	public static class CreateOrderDetailVo {
		private Long orderDetailSeq;
		
		// 発注SEQ
		private Long orderSeq;
		
		// 発注ステータス
		private OrderStatus status;
		
		// 数量
		private Long quantity;
		
		// 納期
		private String deriveryDate;
		
	}
}

