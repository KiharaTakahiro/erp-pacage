package com.erp.main.app.controller.supplier.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.erp.main.domain.common.enums.OrderStatus;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;

import lombok.Data;

/**
 * 発注作成用のリクエスト
 * @author takayama
 *
 */
@Data
public class CreateOrderRequest {
	
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
	public static class CreateOrderDetailRequest {		

		// 発注ステータス
		private OrderStatus status;
		
		// 数量
		@NotNull
		private Long discount;
		
		// 納期
		@NotNull
		private String deriveryDate;
		
	}
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	
	public CreateOrderVo mapTo() {
		
		var vo = new CreateOrderVo();

		// 仕入先SEQ
		vo.setSupplierSeq(this.getSupplierSeq());
		// 発注金額
		vo.setTotal(this.getTotal());
		// 消費税
		vo.setTax(this.getTax());		
		return vo;
		
	}
}
