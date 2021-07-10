package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;

@Data
public class GetClientsVo {
	
	// 取引先リスト
	List<ClientModel> clients;
	// 全体のページ数
	Integer maxpage;
}
