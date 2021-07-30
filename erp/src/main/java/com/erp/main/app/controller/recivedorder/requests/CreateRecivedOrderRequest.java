package com.erp.main.app.controller.recivedorder.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.erp.main.app.annotations.datevalidation.DateValidation;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo.CreateRecivedOrderDetailVo;

import lombok.Data;

/**
 * 受注作成用のリクエスト
 * @author 永戸
 *
 */
@Data
public class CreateRecivedOrderRequest {

	// 取引先SEQ
	@NotNull
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 部署SEQ
	private Long departmentSeq;
	
	// 見積SEQ
	private Long quotationSeq;
	
	// 受注日
	@DateValidation
	private String recivedOrderDate;
	
	// 消費税
	@NotNull
	private Long tax;
	
	// 合計金額
	@NotNull
	private Long total;
	
	// 受注詳細
	private List<CreateRecivedOrderDetailRequest> details;
	
	/**
	 * 受注詳細のリクエスト
	 * @author ngt
	 *
	 */
	@Data
	public static class CreateRecivedOrderDetailRequest {

		// 配送日
		@DateValidation
		private String deriveryDate;
		
		// 値引
		@NotNull
		private Long discount;
		
		// ロットSEQ
		@NotNull
		private Long lotSeq;
		
		// 商品SEQ
		@NotNull
		private Long productSeq;
		
		// 数量
		@NotNull
		private Integer quantity;
		
		// 状態
		@NotNull
		private Integer status;
				
	}
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateRecivedOrderVo mapTo() {
		
		var vo = new CreateRecivedOrderVo();
		
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		vo.setCompanySeq(this.getCompanySeq());
		// 部署SEQ
		vo.setDepartmentSeq(this.getDepartmentSeq());
		// 見積SEQ
		vo.setQuotationSeq(this.getQuotationSeq());
		// 受注日
		vo.setRecivedOrderDate(this.getRecivedOrderDate());
		// 消費税
		vo.setTax(this.getTax());
		// 合計金額
		vo.setTotal(this.getTotal());
		
		// 詳細
		List<CreateRecivedOrderDetailVo> detailVos = new ArrayList<>();
		
		for(CreateRecivedOrderDetailRequest detail: this.getDetails()) {
			var detailVo = new CreateRecivedOrderDetailVo();
			
			//配送日
			detailVo.setDeriveryDate(detail.getDeriveryDate());		
			// 商品SEQ
			detailVo.setProductSeq(detail.getProductSeq());
			// 数量
			detailVo.setQuantity(detail.getQuantity());
			// 値引
			detailVo.setDiscount(detail.getDiscount());
			// 状態
			detailVo.setStatus(detail.getStatus());
			detailVos.add(detailVo);
		}
		
		// 詳細の追加
		vo.setDetails(detailVos);
		
		return vo;
	}
}
