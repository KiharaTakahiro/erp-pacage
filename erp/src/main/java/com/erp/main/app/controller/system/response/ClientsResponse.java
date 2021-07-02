package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.ClientModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取引先一覧
 * @author NGT
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ClientsResponse extends BaseResponse {
	// 最大ページ数
	private Integer maxpage;
	
	// 見積リスト
	List<ClientModel> clients;
}
