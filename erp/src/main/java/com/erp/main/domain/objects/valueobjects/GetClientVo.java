package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.ClientsEntity;
import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;

@Data
public class GetClientVo {
	private ClientModel client;
	
	public static GetClientVo mapTo(ClientsEntity entity) {
		
		GetClientVo vo = new GetClientVo();
		ClientModel model = new ClientModel();
		model.setClientsSeq(entity.getClientsSeq());
		model.setClientsName(entity.getName());
		vo.setClient(model);
		
		
		return vo;
		
	}
}
