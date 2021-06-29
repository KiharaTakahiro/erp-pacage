package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.UpdateClientVo;

import lombok.Data;

/*
 * 取引先編集のリクエスト
 * @author ngt
 */
@Data
public class UpdateClientRequest {
	
	// 取引先SEQ
	private Long clientsSeq;
	
	// 取引先名
	private String clientsName;
	
	
	/**
	 * リクエスト空ValueObjectへのマッピング
	 * @return
	 */
	public UpdateClientVo mapTo() {
		
		var vo = new UpdateClientVo();
		
		// 取引先SEQ
		vo.getClient().setClientsSeq(this.getClientsSeq());
		
		// 取引先名
		vo.getClient().setClientsName(this.getClientsName());

		return vo;
	}
}
