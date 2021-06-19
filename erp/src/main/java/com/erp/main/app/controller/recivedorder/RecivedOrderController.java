package com.erp.main.app.controller.recivedorder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.recivedorder.requests.CreateRecivedOrderRequest;
import com.erp.main.domain.services.RecivedOrderService;

/*
 * 受注コントローラー
 * @author nagato
 */
@RestController
public class RecivedOrderController {
	
	/**
	 * 受注サービス
	 */
	@Autowired
	private RecivedOrderService recivedOrderService;
	
	/**
	 * 見積作成
	 * @param request
	 */
	@PostMapping("/recivedorder/register")
	public void createRecivedOrder(@Valid @RequestBody CreateRecivedOrderRequest request) {
		this.recivedOrderService.createRecivedOrder(request.mapTo());
	}
	


}
