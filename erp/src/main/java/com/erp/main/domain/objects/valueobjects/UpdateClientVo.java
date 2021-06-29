package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;

/*
 * 取引先編集処理のVo
 * @author ngt
 */
@Data
public class UpdateClientVo {
	// 取引先
	private ClientModel client;

}
