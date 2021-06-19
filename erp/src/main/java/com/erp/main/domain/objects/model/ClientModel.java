package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 取引先のモデル
 * @author takah
 *
 */
@Data
public class ClientModel {
	
	// 取引先SEQ
	private Long clientsSeq;
	
	// 取引先名
	private String clientsName;
}
