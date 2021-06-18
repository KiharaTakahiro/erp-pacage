package com.erp.main.app.controller.sales.requests;

import com.erp.main.domain.objects.valueobjects.GetQuotationConditionsVo;

import lombok.Data;

/**
 * 見積取得用のリクエスト
 * @author takah
 *
 */
@Data
public class GetQuotationRequest {

	// 取引先SEQ
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 部署SEQ
	private Long departmentSeq;
	
	// 作成日From
	private String createDateFrom;
	
	// 作成日To
	private String createDateTo;
	
	// 見積番号
	private String quotationNo;
	
	// 件名
	private String subject;
	
	/**
	 * リクエストをVoにマッピング
	 * @return
	 */
	public GetQuotationConditionsVo mapTo() {
		GetQuotationConditionsVo vo = new GetQuotationConditionsVo();
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());
		// 会社SEQ
		vo.setCompanySeq(this.getCompanySeq());
		// 部署SEQ
		vo.setDepartmentSeq(this.getDepartmentSeq());
		// 作成日From
		vo.setCreateDateFrom(this.getCreateDateFrom());
		// 作成日To
		vo.setCreateDateTo(this.getCreateDateTo());
		// 見積番号
		vo.setQuotationNo(this.getQuotationNo());
		// 件名
		vo.setSubject(this.getSubject());
		return vo;
		
	}
	
}
