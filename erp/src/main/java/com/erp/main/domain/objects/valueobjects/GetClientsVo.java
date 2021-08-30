package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;
/*
 * 取引先の取得VO
 * @author ngt
 */
@Data
public class GetClientsVo {
	
	// 取引先リスト
	List<ClientModel> clients;
	// 全体のページ数
	Long totalItemsNum;
}
