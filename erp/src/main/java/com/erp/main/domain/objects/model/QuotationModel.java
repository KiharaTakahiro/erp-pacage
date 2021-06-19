package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 見積のモデル
 * @author takah
 *
 */
@Data
public class QuotationModel {

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
