package com.erp.main.domain.objects.model;

import lombok.Data;

/*
 * 受注票のモデル
 * @author ngt
 */
@Data
public class RecivedOrderModel {
	// 受注Seq
	private Long recivedOrderSeq;
	//取引先Seq
	private Long clientsSeq;
	//会社Seq
	private Long companySeq;
	//部署Seq
	private Long departmentSeq;
	//見積Seq
	private Long quotationSeq;
	//受注日
	private String recivedOrderDate;
	//税金
	private Long tax;
	//合計金額
	private Long total;
}
