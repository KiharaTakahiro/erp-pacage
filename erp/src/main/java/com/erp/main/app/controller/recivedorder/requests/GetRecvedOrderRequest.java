package com.erp.main.app.controller.recivedorder.requests;

import lombok.Data;

/*
 * 受注詳細取得リクエスト
 * @author ngt
 */
@Data
public class GetRecvedOrderRequest {
	// 受注票Seq
	private Long RecivedOrderSeq;
}
