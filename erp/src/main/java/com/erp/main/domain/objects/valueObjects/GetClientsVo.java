package com.erp.main.domain.objects.valueObjects;

import java.util.ArrayList;
import java.util.List;

import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;

@Data
public class GetClientsVo {
	
	List<ClientModel> clients = new ArrayList<ClientModel>();
	
	Integer maxpage;
}
