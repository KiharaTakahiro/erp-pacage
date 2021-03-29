package com.erp.main.app.controller.system.response;

import lombok.Data;

/**
 * レスポンスの基底クラス
 * @author takah
 *
 */
@Data
public class BaseResponse {

	/**
	 * 結果
	 */
	private Boolean result;
	
	/**
	 * メッセージ
	 */
	private String message;
}
