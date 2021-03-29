package com.erp.main.domain.objects.valueObjects;

import lombok.Data;

/**
 * ユーザ作成のためのValueObject
 * @author 木原
 *
 */
@Data
public class CreateUserVo {

	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * 姓
	 */
	private String firstName;
	
	/**
	 * 名
	 */
	private String lastName;

	/**
	 * Email 
	 */
	private String email;
}
