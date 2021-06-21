package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetClientsConditionsVo;

import lombok.Data;

/*
 * 取引先取得のリクエスト
 * @author ngt
 */
@Data
public class GetClientRequest {
	
	// 取引先SEQ
	private Long clientsSeq;
	
	/**
	 * リクエスト空ValueObjectへのマッピング
	 * @return
	 */
	public GetClientsConditionsVo mapTo() {
		
		var vo = new GetClientsConditionsVo();
		
		// 取引先SEQ
		vo.setClientsSeq(this.getClientsSeq());

		return vo;
	}
}
