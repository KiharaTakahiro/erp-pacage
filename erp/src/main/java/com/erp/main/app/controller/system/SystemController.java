package com.erp.main.app.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.system.request.CreateUserRequest;
import com.erp.main.app.controller.system.response.CreateUserResponse;
import com.erp.main.domain.services.UserService;

/**
 * システム関連エントリーポイントのコントローラ
 * @author 木原
 *
 */
@RestController
public class SystemController {

	/**
	 * ユーザ関連のサービス
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * ユーザ作成用のエントリーポイント
	 * @param request
	 * @return
	 */
	@PostMapping("/user/register")
	private CreateUserResponse createUser(CreateUserRequest request) {
		this.userService.registerUser(request.mapTo());
		CreateUserResponse response = new CreateUserResponse();
		return response;
	}
}
