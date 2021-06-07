package com.erp.main.domain.objects.valueObjects;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GetClientsVo {
	
	List<Clients> clients = new ArrayList<Clients>();
	
	Integer maxpage;
	
	@Data
	public static class Clients{
		private Long clientsSeq;
		private String clientsName;
		
	}
	
}
