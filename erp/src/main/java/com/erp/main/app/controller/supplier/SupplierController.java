package com.erp.main.app.controller.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.recivedorder.requests.GetSupplierRequest;
import com.erp.main.app.controller.recivedorder.requests.UpdateSupplierRequest;
import com.erp.main.app.controller.recivedorder.response.SupplierResponse;
import com.erp.main.app.controller.recivedorder.response.SuppliersResponse;
import com.erp.main.app.controller.supplier.request.CreateOrderRequest;
import com.erp.main.app.controller.supplier.request.CreateSupplierProductRequest;
import com.erp.main.app.controller.system.request.CreateSupplierRequest;
import com.erp.main.app.controller.system.request.GetSupplierProductRequest;
import com.erp.main.app.controller.system.response.SupplierProductsResponse;
import com.erp.main.domain.services.SupplierService;


/**
 * 発注コントローラ
 * @author takah
 *
 */
@RestController
public class SupplierController{
	
	/**
	 * 受注サービス
	 */
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 発注作成
	 * @param request
	 */
	@PostMapping("/order/register")
	public void createOrder(@RequestBody CreateOrderRequest request) {
		this.supplierService.createOrder(request.mapTo());
	}
	
	/*
	 * 仕入商品作成用のエントリーポイント
	 * @param request
	 */
	@PostMapping("/supplier-product/register")
	public void createSupplierProduct(@RequestBody CreateSupplierProductRequest request) {
		this.supplierService.createSupplierProduct(request.mapTo());
	}
	
	/*
	 * 仕入れ商品一覧処理
	 */
	@PostMapping("/supplier-products/info")
	public SupplierProductsResponse infosupplierSerrvice(@RequestBody GetSupplierProductRequest request) {
		var vo = this.supplierService.getSupplierProductsVo(request.mapTo());
		var response = new SupplierProductsResponse();
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setSupplierProduct(vo.getSupplierProduct());
		return response;
	}


	/**
	 * 仕入れ先作成用のエントリーポイント
	 * @param req
	 */
	@PostMapping("/supplier/register")
	public void createSupplier(@RequestBody CreateSupplierRequest request) {
		this.supplierService.createSupplier(request.mapTo());
	}
	
	/*
	 * 仕入先プルダウンのエントリーポイント
	 */
	@GetMapping("/supplier/pulldown")
	public SuppliersResponse pullDownpSupplier( ) {
		var vo = this.supplierService.pullDownSupplier();
		var response = new SuppliersResponse();
		response.setSupplier(vo.getSupplier());
		return response;
	}
	
	/*
	 * 仕入先一覧処理のエントリーポイント
	 */
	@PostMapping("/supplier/info")
	public SuppliersResponse infoSupplier(@RequestBody GetSupplierRequest request) {
		var vo = this.supplierService.getSupplierVo(request.mapTo());
		var response = new SuppliersResponse();
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setSupplier(vo.getSupplier());
		return response;

	}
	
	/*
	 * 仕入先詳細取得のエントリーポイント
	 * @param responce
	 */
	@PostMapping("/supplier/edit")
	public SupplierResponse getClient(@RequestBody GetSupplierRequest request) {
		Long id = request.getSupplierSeq(); 
		var vo = this.supplierService.getSupplierVo(id);
		return SupplierResponse.mapTo(vo);
		
	}
	
	/**
	 * 仕入先更新処理のエントリーポイント
	 * @param request
	 */
	@PostMapping("/supplier/update")
	public void updateSupplier(@RequestBody UpdateSupplierRequest request) {
		this.supplierService.updateSupplier(request.mapTo());
	}
	


	

}