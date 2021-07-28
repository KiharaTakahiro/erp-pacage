package com.erp.main.app.controller.supplier.request;

import java.util.ArrayList;
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
	
	private List<CreateOrderDetailRequest> details;
	
	/**
	 * 発注詳細のリクエスト
	 * @author 高山
	 *
	 */
	@Data
	public static class CreateOrderDetailRequest {		

		// 仕入商品Seq
		private Long supplierProductSeq;
		
		// 発注ステータス
		private OrderStatus status;
		
		// 数量
		@NotNull
		private Integer quantity;
		
		// 納期
		@NotNull
		private String deriveryDate;
		
	}
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return vo
	 */
	
	public CreateOrderVo mapTo() {
		
		var vo = new CreateOrderVo();

		// 仕入先SEQ
		vo.setSupplierSeq(this.getSupplierSeq());
		// 発注金額
		vo.setTotal(this.getTotal());
		// 消費税
		vo.setTax(this.getTax());		
		
		// 発注詳細
		List<CreateOrderDetailVo> detailVos = new ArrayList<>();
		
		for(CreateOrderDetailRequest detail: this.getDetails()) {
			var detailVo = new CreateOrderDetailVo();
			
			// 仕入商品Seq
			detailVo.setSupplierProductSeq(detail.getSupplierProductSeq());
			// 発注ステータス
			detailVo.setStatus(detail.getStatus());;
			// 数量
			detailVo.setQuantity(detail.getQuantity());
			// 納期
			detailVo.setDeriveryDate(detail.getDeriveryDate());
			
			detailVos.add(detailVo);
		}


		// 発注詳細の追加
		vo.setDetails(detailVos);
			
		
		return vo;
		
	}
}
