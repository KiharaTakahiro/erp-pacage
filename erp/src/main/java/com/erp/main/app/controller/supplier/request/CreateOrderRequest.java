package com.erp.main.app.controller.supplier.request;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.erp.main.domain.common.enums.OrderStatus;

import lombok.Data;

/**
 * 発注作成用のリクエスト
 * @author takayama
 *
 */
@Data
public class CreateOrderRequest {
	
	// 発注SEQ
	@NotNull
	private Long orderSeq;
	
	// 仕入先SEQ
	private Long supplierSeq;
	
	// 発注金額
	private Long total;
	
	// 消費税
	private Long tax;
	
	/**
	 * 発注詳細のリクエスト
	 * @author ngt
	 *
	 */
	@Data
	public static class CreateOrderDetailRequest {

		// 発注詳細SEQ
		@Id
		private Long orderDetailSeq;
		
		// 発注SEQ
		private Long orderSeq;
		
		// 発注ステータス
		private OrderStatus status;
		
		// 数量
		@NotNull
		private Long discount;
		
		// 納期
		@NotNull
		private String deriveryDate;
		
	}
}
