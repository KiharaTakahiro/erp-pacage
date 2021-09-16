package com.erp.main.app.controller.system;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.sales.response.GetDepartmentsRequest;
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
import com.erp.main.app.controller.system.request.GetCodeRequest;
import com.erp.main.app.controller.system.request.GetProductRequest;
import com.erp.main.app.controller.system.request.GetProductsRequest;
import com.erp.main.app.controller.system.request.GetWarehouseRequest;
import com.erp.main.app.controller.system.request.UpdateClientRequest;
import com.erp.main.app.controller.system.request.UpdateProductsRequest;
import com.erp.main.app.controller.system.response.ClientResponse;
import com.erp.main.app.controller.system.response.ClientsResponse;
import com.erp.main.app.controller.system.response.CompanysResponse;
import com.erp.main.app.controller.system.response.DepatmentsResponse;
import com.erp.main.app.controller.system.response.GetCodeResponse;
import com.erp.main.app.controller.system.response.ProductResponse;
import com.erp.main.app.controller.system.response.ProductsResponse;
import com.erp.main.app.controller.system.response.ProductsTableResponse;
import com.erp.main.app.controller.system.response.WarehouseResponse;
import com.erp.main.domain.services.MasterService;
import com.erp.main.domain.services.SystemService;
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
	 * システム関連サービス
	 */
	@Autowired
	private SystemService systemService;
	
	/**
	 * フィルタでエラーが発生した場合にマッピングされる
	 * @param response
	 * @return
	 */
	@RequestMapping("/error_filter")
	public HttpServletResponse jwtError(HttpServletResponse response) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return response;
	}
	
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
	
	/*
	 * 商品詳細取得のエントリーポイント
	 * @param responce
	 */
	@PostMapping("/product/edit")
	public ProductResponse getClient(@RequestBody GetProductRequest request) {
		Long id = request.getProductSeq(); 
		var vo = this.masterService.getProductVo(id);
		return ProductResponse.mapTo(vo);
		
	}
	
	/**
	 * 商品更新処理のエントリーポイント
	 * @param request
	 */
	@PostMapping("/product/update")
	public void updateProduct(@RequestBody UpdateProductsRequest request) {
		this.masterService.updateProducts(request.mapTo());
	}
	
	/*
	 * 商品プルダウンのエントリーポイント
	 * 
	 */
	@GetMapping("/product/pulldown")
	public ProductsResponse pullDownpProduct( ) {
		var vo = this.masterService.pullDownProduct();
		var response = new ProductsResponse();
		response.setProduct(vo.getProduct());
		return response;
	}
	
	/*
	 * 商品一覧処理のエントリーポイント
	 */
	@PostMapping("/product/info")
	public ProductsTableResponse infoProduct(@RequestBody GetProductsRequest request) {
		var vo = this.masterService.getProductsVo(request.mapTo());
		var response = new ProductsTableResponse();
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setProduct(vo.getProduct());
		return response;
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
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setClients(vo.getClients());
		return response;
	}
	
	/**
	 * 取引先プルダウンのエントリーポイント
	 * @param 
	 */
	@GetMapping("/clients/pulldown")
	public ClientsResponse pullDownClients() {
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
	
	/*
	 * 会社プルダウンのエントリーポイント
	 * 
	 */
	@GetMapping("/company/pulldown")
	public CompanysResponse pullDownCompanys( ) {
		var vo = this.masterService.pullDownCompany();
		var response = new CompanysResponse();
		response.setCompanys(vo.getCompany());
		return response;
	}
	
	/**
	 * 部署作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/department/register")
	public void createDepartment(@RequestBody CreateDepartmentRequest request) {
		this.masterService.createDepartment(request.mapTo());
	}
	
	/*
	 * 部署プルダウンのエントリーポイント
	 * 
	 */
	@PostMapping("/deartment/pulldown")
	public DepatmentsResponse pullDownDepartments(@RequestBody GetDepartmentsRequest request) {
		var vo = this.masterService.pullDownDepartment(request.mapTo());
		var response = new DepatmentsResponse();
		response.setDepartments(vo.getDepartment());
		return response;
	}
	
	/**
	 * 倉庫作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/warehouse/register")
	public void createWarehouse(@RequestBody CreateWarehouseRequest request) {
		this.masterService.createWarehouse(request.mapTo());
	}
	
	/*
	 * 倉庫プルダウンのエントリーポイント
	 * 
	 */
	@GetMapping("/warehouse/pulldown")
	public WarehouseResponse pullDownpWarehouse( ) {
		var vo = this.masterService.pullDownWarehouse();
		var response = new WarehouseResponse();
		response.setWarehouse(vo.getWarehouse());
		return response;
	}
	
	/*
	 * 倉庫一覧処理のエントリーポイント
	 */
	@PostMapping("/warehouse/info")
	public WarehouseResponse infoWarehouse(@RequestBody GetWarehouseRequest request) {
		var vo = this.masterService.getWarehouseVo(request.mapTo());
		var response = new WarehouseResponse();
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setWarehouse(vo.getWarehouse());
		return response;

	}
	
	/**
	 * ロット作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/lot/register")
	public void createLot(@RequestBody CreateLotRequest request) {
		this.masterService.createLot(request.mapTo());
	}
	
	/**
	 * コード取得用のエントリーポイント
	 * @param request
	 * @return
	 * @throws ClassNotFoundException 
	 */
	@PostMapping("/system/getcode")
	public GetCodeResponse getCode(@RequestBody GetCodeRequest request) throws ClassNotFoundException {
		var map = systemService.getEnums(request.getCodeType());
		return GetCodeResponse.mapTo(map);
	}
}
