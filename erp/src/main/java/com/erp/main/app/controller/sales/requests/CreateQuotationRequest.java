package com.erp.main.app.controller.sales.requests;

import java.util.ArrayList;
import java.util.List;

import com.erp.main.domain.objects.valueObjects.CreateQuotationVo;
import com.erp.main.domain.objects.valueObjects.CreateQuotationVo.CreateQuotationDetailVo;

import lombok.Data;

/**
 * 見積作成用のリクエスト
 * @author takah
 *
 */
@Data
public class CreateQuotationRequest {
	
	// 取引先SEQ
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 見積番号
	private String quotationNo;
	
	// 作成日
	private String createDate;
	
	// 件名
	private String subject;
	
	// 値引合計
	private Long discountTotal;
	
	private List<CreateDetailRequest> details;
	
	@Data
	public static class CreateDetailRequest {

		// 商品SEQ
		private Long productSeq;
		
		// 数量
		private Integer quantity;
		
		// 値引
		private Long discount;
	}
	
	public CreateQuotationVo mapTo() {
		CreateQuotationVo vo = new CreateQuotationVo();
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		vo.setCompanySeq(this.getCompanySeq());
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
			CreateQuotationDetailVo detailVo = (new CreateQuotationVo()).new CreateQuotationDetailVo();
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
