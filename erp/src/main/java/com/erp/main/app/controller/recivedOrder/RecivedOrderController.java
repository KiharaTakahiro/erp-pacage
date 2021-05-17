package com.erp.main.app.controller.recivedOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.domain.services.QuotationService;

/*
 * 受注コントローラー
 * @author nagato
 */
@RestController
public class RecivedOrderController {
	@Autowired
	private QuotationService recivedOrderService;
	/**
	 * 見積作成
	 * @param request
	 */
	@PostMapping("/recivedorder/register")
	public void createRecivedOrder(@RequestBody CreateRecivedOrderRequest request) {
		this.recivedOrderService.createRecivedOrder(request.mapTo());
	}
	


}
