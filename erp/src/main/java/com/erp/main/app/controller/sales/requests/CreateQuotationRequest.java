package com.erp.main.app.controller.sales.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.erp.main.domain.objects.valueobjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueobjects.CreateQuotationVo.CreateQuotationDetailVo;

import lombok.Data;

/**
 * 見積作成用のリクエスト
 * @author takah
 *
 */
@Data
public class CreateQuotationRequest {
	
	// 取引先SEQ
	@NotNull
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 部署SEQ
	private Long departmentSeq;
	
	// 見積番号
	@NotNull
	private String quotationNo;
	
	// 作成日
	private String createDate;
	
	// 件名
	@NotNull
	private String subject;
	
	// 値引合計
	@NotNull
	private Long discountTotal;
	
	// 見積詳細
	private List<CreateDetailRequest> details;
	
	/**
	 * 見積詳細のための修正
	 * @author takah
	 *
	 */
	@Data
	public static class CreateDetailRequest {

		// 商品SEQ
		@NotNull
		private Long productSeq;

		// 数量
		@NotNull
		private Integer quantity;
		
		// 値引
		@NotNull
		private Long discount;
	}
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateQuotationVo mapTo() {
		
		var vo = new CreateQuotationVo();
		
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		vo.setCompanySeq(this.getCompanySeq());
		// 部署SEQ
		vo.setDepartmentSeq(this.getDepartmentSeq());
		// 見積番号
		vo.setQuotationNo(this.getQuotationNo());
		// 作成日
		vo.setCreateDate(this.getCreateDate());
		// 件名
		vo.setSubject(this.getSubject());
		// 値引合計
		vo.setDiscountTotal(this.getDiscountTotal());
		
		// 値引詳細
		List<CreateQuotationDetailVo> detailVos = new ArrayList<>();
		for(CreateDetailRequest detail: this.getDetails()) {

			var detailVo = new CreateQuotationDetailVo();

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
