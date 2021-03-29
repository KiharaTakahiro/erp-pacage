package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueObjects.CreateUserVo;

import lombok.Data;

/**
 * ユーザ作成APIのリクエスト
 * @author 木原
 *
 */
@Data
public class CreateUserRequest {
	
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
	
	/**
	 * ユーザ作成用のValueObjectへのマッピング
	 * @return
	 */
	public CreateUserVo mapTo() {
		CreateUserVo vo = new CreateUserVo();
		// ユーザID
		vo.setUserId(this.getUserId());
		// パスワード
		vo.setPassword(this.getPassword());
		// 姓
		vo.setFirstName(this.getFirstName());
		// 名
		vo.setLastName(this.getLastName());
		// email
		vo.setEmail(this.getEmail());
		return vo;
	}
}
