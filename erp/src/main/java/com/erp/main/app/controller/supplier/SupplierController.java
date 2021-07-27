package com.erp.main.app.controller.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.supplier.request.CreateOrderRequest;
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
	

}
