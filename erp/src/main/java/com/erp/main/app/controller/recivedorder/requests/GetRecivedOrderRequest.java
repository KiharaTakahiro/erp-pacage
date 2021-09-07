package com.erp.main.app.controller.recivedorder.requests;

import lombok.Data;

/*
 * 受注一覧取得用リクエスト
 */
@Data
public class GetRecivedOrderRequest {
	
	//ページ番号
	private Integer totalPageNum;
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
