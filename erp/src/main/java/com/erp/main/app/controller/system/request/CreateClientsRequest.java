package com.erp.main.app.controller.system.request;

import javax.validation.constraints.Max;

import com.erp.main.domain.objects.valueobjects.CreateClientsVo;

import lombok.Data;

@Data
public class CreateClientsRequest {
	
	/**
	 * 取引先名
	 */
	
	@Max(2)
	private String clientsName;
	
	public CreateClientsVo mapTo() {
		CreateClientsVo vo = new CreateClientsVo();
		// 商品名
		vo.setClientsName(this.getClientsName());
		return vo;
	}

}
