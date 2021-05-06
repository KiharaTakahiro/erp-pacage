package com.erp.main.app.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.system.request.CreateClientsRequest;
import com.erp.main.app.controller.system.request.CreateProductRequest;
import com.erp.main.app.controller.system.request.CreateSupplierRequest;
import com.erp.main.app.controller.system.request.CreateUserRequest;
import com.erp.main.app.controller.system.response.CreateUserResponse;
import com.erp.main.domain.services.MasterService;
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
	 * マスタ関連サービス
	 */
	@Autowired
	private MasterService masterService;
	
	/**
	 * ユーザ作成用のエントリーポイント
	 * @param request
	 * @return
	 */
	@PostMapping("/users/register")
	public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
		this.userService.registerUser(request.mapTo());
		CreateUserResponse response = new CreateUserResponse();
		return response;
	}
	
	/**
	 * 商品作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/product/register")
	public void createProduct(@RequestBody CreateProductRequest request) {
		this.masterService.createProduct(request.mapTo());
	}
	
	/**
	 * 仕入れ先作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/supplier/register")
	public void createSupplier(@RequestBody CreateSupplierRequest request) {
		this.masterService.createSupplier(request.mapTo());
	}
	
	/**
	 * 取引先作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/clients/register")
	public void createClients(@RequestBody CreateClientsRequest request) {
		this.masterService.createClients(request.mapTo());
	}
}
