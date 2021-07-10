package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/*
 * 取引先取得用の検索条件
 */

@Data
public class GetClientsConditionsVo {
	// ページ番号
	private Integer pageNo;
	// 取引先Seq
	private Long clientsSeq;
	// 取引先名
	private String clientsName;
}
