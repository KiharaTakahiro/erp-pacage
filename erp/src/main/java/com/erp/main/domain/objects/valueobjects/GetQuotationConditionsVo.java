package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 見積取得用の検索条件
 * @author takah
 *
 */
@Data
public class GetQuotationConditionsVo {

	// ページ番号(TODO: 別でConditonように作る)
	private Integer pageNo;
	
	// 見積SEQ
	private Long quotationSeq;
	
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
}
