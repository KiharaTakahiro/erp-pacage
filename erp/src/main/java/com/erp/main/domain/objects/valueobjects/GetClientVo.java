package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;
/*
 * 取引先取得用のVO
 * @author ngt
 */
@Data
public class GetClientVo {
	private ClientModel client;
	
	public static GetClientVo mapTo(ClientsEntity entity) {
		
		var vo = new GetClientVo();
		var model = new ClientModel();
		model.setClientsSeq(entity.getClientsSeq());
		model.setClientsName(entity.getName());
		vo.setClient(model);
				
		return vo;
		
	}
}
