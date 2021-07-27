package com.erp.main.app.controller.supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.erp.main.app.controller.supplier.request.CreateOrderRequest;
import com.erp.main.domain.services.SupplierService;

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
	public void createOrder(@Valid @RequestBody CreateOrderRequest request) {
		this.supplierService.createOrder(request.mapTo());
	}
	

}
