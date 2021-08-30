package com.erp.main.app.controller.system.request;

import lombok.Data;


/**
 * コード取得用リクエスト
 * @author takah
 *
 */
@Data
public class GetCodeRequest {
	
	// コードの種類
	private String codeType;
}
