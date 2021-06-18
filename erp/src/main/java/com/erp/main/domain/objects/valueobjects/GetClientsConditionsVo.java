package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/*
 * 取引先取得用の検索条件
 */

@Data
public class GetClientsConditionsVo {
	
	private Integer pageNo;
	private Long clientsSeq;
	private String clientName;
}
