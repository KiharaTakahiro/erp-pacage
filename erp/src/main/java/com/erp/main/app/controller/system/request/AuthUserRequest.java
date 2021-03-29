package com.erp.main.app.controller.system.request;

import lombok.Data;

@Data
public class AuthUserRequest {

	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * パスワード
	 */
	private String password;
}
