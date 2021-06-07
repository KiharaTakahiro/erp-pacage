package com.erp.main.app.controller.sales.response;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積一覧
 * @author takah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class QuotationResponse extends BaseResponse {

	// 最大ページ数
	private Integer maxpage;
	
	// 見積リスト
	List<Quotation> quotations;
	
	/**
	 * 見積
	 * @author takah
	 *
	 */
	@Data
	public static class Quotation {
		
		// 取引先SEQ
		private Long clientsSeq;
		
		// 取引先名称
		private String clientsName;
		
		// 会社SEQ
		private Long companySeq;
		
		// 会社名
		private String companyName;
		
		// 部署SEQ
		private Long departmentSeq;
		
		// 部署名
		private String departmentName;
		
		// 作成日
		private String createDate;
		
		// 見積番号
		private String quotationNo;
		
		// 件名
		private String subject;
	}
}
