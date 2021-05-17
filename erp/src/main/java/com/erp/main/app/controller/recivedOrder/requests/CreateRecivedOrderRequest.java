package com.erp.main.app.controller.recivedOrder.requests;

import java.util.ArrayList;
import java.util.List;

import com.erp.main.app.controller.sales.requests.CreateQuotationRequest.CreateDetailRequest;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo.CreateQuotationDetailVo;
import com.erp.main.domain.objects.valueObjects.CreateRecivedOrderVo;
import com.sun.istack.NotNull;

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
	private String recivedOrderDate;
	
	// 消費税
	@NotNull
	private Long tax;
	
	// 合計金額
	@NotNull
	private Long total;
	
	private List<CreateRecivedOrderDetailRequest> details;
	
	/**
	 * 受注詳細のリクエスト
	 * @author ngt
	 *
	 */
	@Data
	public static class CreateRecivedOrderDetailRequest {

		// 配送日
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
		
		// 受注SEQ
		private String recivedOrderSeq;
		
	}
	
	public CreateRecivedOrderVo mapTo() {
		CreateRecivedOrderVo vo = new CreateRecivedOrderVo();
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		vo.setCompanySeq(this.getCompanySeq());
		// 部署SEQ
		vo.setDepartmentSeq(this.getDepartmentSeq());
		// 見積番号
		vo.setQuotationSeq(this.getQuotationSeq());
		// 作成日
		vo.setRecivedOrderDate(this.getRecivedOrderDate());
		// 件名
		vo.setTax(this.getTax());
		// 値引合計
		vo.setTotal(this.getTotal());
		
		// 値引詳細
		List<CreateQuotationDetailVo> detailVos = new ArrayList<>();
		for(CreateDetailRequest detail: this.getDetails()) {
			CreateQuotationDetailVo detailVo = new CreateQuotationDetailVo();
			// 商品SEQ
			detailVo.setProductSeq(detail.getProductSeq());
			// 数量
			detailVo.setQuantity(detail.getQuantity());
			// 値引
			detailVo.setDiscount(detail.getDiscount());
			detailVos.add(detailVo);
		}
		
		// 値引詳細の追加
		vo.setDetails(detailVos);
		
		return vo;
	}
}
