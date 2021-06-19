package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateClientsVo;

import lombok.Data;

/**
 * 取引先作成リクエスト
 * @author takah
 *
 */
@Data
public class CreateClientsRequest {
	
	// 取引先名
	private String clientsName;
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateClientsVo mapTo() {
		
		var vo = new CreateClientsVo();
		
		// 商品名
		vo.setClientsName(this.getClientsName());
		
		return vo;
	}

}
