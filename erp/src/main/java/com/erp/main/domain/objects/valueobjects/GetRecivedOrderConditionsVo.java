package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/*
 * 受注票取得の検索条件
 */
@Data
public class GetRecivedOrderConditionsVo {
	// ページ番号
	private Integer pageNo;
	//受注Seq
	private Long recivedOrderSeq;
	//取引先Seq
	private Long clientsSeq;
	//会社Seq
	private Long companySeq;
	//部署Seq
	private Long departmentSeq;
	//日付から
	private String fromDate;
	//日付まで
	private String toDate;
	//税金から
	private Integer fromTax;
	//税金まで
	private Integer toTax;
	//金額から
	private Integer fromTotal;
	//金額まで
	private Integer toTotal;
}
