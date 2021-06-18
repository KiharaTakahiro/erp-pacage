package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateClientsVo;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CreateClientsRequest {
	
	/**
	 * 取引先名
	 */
	@NotNull
	private String clientsName;
	
	public CreateClientsVo mapTo() {
		CreateClientsVo vo = new CreateClientsVo();
		// 商品名
		vo.setClientsName(this.getClientsName());
		return vo;
	}

}
