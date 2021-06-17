package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import lombok.Data;

/**
 * 見積用のValueObject
 * @author takah
 *
 */
@Data
public class GetQuotationVo {

	// 見積リスト
	List<Quotation> quotations ;
	
	// 全体のページ数
	Integer maxpage;
	
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
