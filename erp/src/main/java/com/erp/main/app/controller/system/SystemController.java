package com.erp.main.app.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.system.request.CreateClientsRequest;
import com.erp.main.app.controller.system.request.CreateCompanyRequest;
import com.erp.main.app.controller.system.request.CreateDepartmentRequest;
import com.erp.main.app.controller.system.request.CreateLotRequest;
import com.erp.main.app.controller.system.request.CreateProductRequest;
import com.erp.main.app.controller.system.request.CreateSupplierRequest;
import com.erp.main.app.controller.system.request.CreateUserRequest;
import com.erp.main.app.controller.system.request.CreateWarehouseRequest;
import com.erp.main.app.controller.system.request.GetClientRequest;
import com.erp.main.app.controller.system.response.ClientResponse;
import com.erp.main.app.controller.system.response.CreateUserResponse;
import com.erp.main.domain.objects.valueobjects.GetClientVo;
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
	public void createClients( @RequestBody CreateClientsRequest request) {
		this.masterService.createClients(request.mapTo());
	}
	
	/**
	 * 会社作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/company/register")
	public void createCompany(@RequestBody CreateCompanyRequest request) {
		this.masterService.createCompany(request.mapTo());
	}
	
	
	/**
	 * 部署作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/department/register")
	public void createDepartment(@RequestBody CreateDepartmentRequest request) {
		this.masterService.createDepartment(request.mapTo());
	}
	
	/**
	 * 倉庫作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/warehouse/register")
	public void createWarehouse(@RequestBody CreateWarehouseRequest request) {
		this.masterService.createWarehouse(request.mapTo());
	}
	
	/**
	 * ロット作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/lot/register")
	public void createLot(@RequestBody CreateLotRequest request) {
		this.masterService.createLot(request.mapTo());
	}

	/*
	 * 取引先詳細取得のエントリーポイント
	 * @param responce
	 */
	@PostMapping("/clients/edit")
	public ClientResponse getClient(@RequestBody GetClientRequest request) {
		Long id = 2L; 
		GetClientVo vo = this.masterService.getClientVo(id);
		ClientResponse response = new ClientResponse();
		response.setClientsName(vo.getClient().getClientsName());
		response.setClientsSeq(vo.getClient().getClientsSeq());
		return response;
		
	}
	
	/*
	 * 取引先一覧処理
	 */
	// TODO:ID指定できるように変更すること
//	@GetMapping("/clients/info")
//	public ClientsResponse getClients(@RequestBody GetClientsRequest request) {
//		GetClientsVo vo = this.masterService.getClientsVo(request.mapTo());
//		ClientsResponse response = new ClientsResponse();
//		return response
//	}
	
	
	
	
	
	
}
