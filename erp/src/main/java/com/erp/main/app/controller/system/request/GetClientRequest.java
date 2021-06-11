package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueObjects.GetClientsConditionsVo;

import lombok.Data;

/*
 * 取引先取得のリクエスト
 * @author ngt
 */

@Data
public class GetClientRequest {
	private Long clientsSeq;
	private String clientsName;
	
	public GetClientsConditionsVo mapTo() {
		GetClientsConditionsVo vo = new GetClientsConditionsVo();
		vo.setClientsSeq(this.getClientsSeq());
		vo.setClientName(this.getClientsName());
		
		return vo;
	}
}
