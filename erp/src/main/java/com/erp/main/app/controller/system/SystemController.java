package com.erp.main.app.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.supplier.request.CreateSupplierProductRequest;
import com.erp.main.app.controller.system.request.CreateClientsRequest;
import com.erp.main.app.controller.system.request.CreateCompanyRequest;
import com.erp.main.app.controller.system.request.CreateDepartmentRequest;
import com.erp.main.app.controller.system.request.CreateLotRequest;
import com.erp.main.app.controller.system.request.CreateProductRequest;
import com.erp.main.app.controller.system.request.CreateSupplierRequest;
import com.erp.main.app.controller.system.request.CreateUserRequest;
import com.erp.main.app.controller.system.request.CreateWarehouseRequest;
import com.erp.main.app.controller.system.request.GetClientRequest;
import com.erp.main.app.controller.system.request.GetClientsRequest;
import com.erp.main.app.controller.system.request.UpdateClientRequest;
import com.erp.main.app.controller.system.response.ClientResponse;
import com.erp.main.app.controller.system.response.ClientsResponse;
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
	public void createUser(@RequestBody CreateUserRequest request) {
		this.userService.registerUser(request.mapTo());
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
	
	/*
	 * 仕入商品作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/supplier-product/register")
	public void createSupplierProduct(@RequestBody CreateSupplierProductRequest request) {
		this.masterService.createSupplierProduct(request.mapTo());
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
	 * 取引先プルダウンのエントリーポイント
	 * @param req
	 */
	@GetMapping("/clients/pulldown")
	public ClientsResponse pullDownClients( ) {
		var vo = this.masterService.pullDownClients();
		var response = new ClientsResponse();
		response.setClients(vo.getClients());
		return response;
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
		Long id = request.getClientsSeq(); 
		var vo = this.masterService.getClientVo(id);
		var response = new ClientResponse();
		response.setClientsName(vo.getClient().getClientsName());
		response.setClientsSeq(vo.getClient().getClientsSeq());
		return response;
		
	}
	
	/*
	 * 取引先更新処理
	 * @param req
	 */
	@PostMapping("/client/update")
	public void updateClient(@RequestBody UpdateClientRequest request) {
		this.masterService.updateClient(request.mapTo());
	}
	
	/*
	 * 取引先一覧処理
	 */
	@PostMapping("/clients/info")
	public ClientsResponse infoClients(@RequestBody GetClientsRequest request) {
		var vo = this.masterService.getClientsVo(request.mapTo());
		var response = new ClientsResponse();
		response.setMaxpage(vo.getMaxpage());
		response.setClients(vo.getClients());
		return response;
	}	
}
