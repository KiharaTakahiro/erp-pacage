package com.erp.main.app.controller.recivedorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.recivedorder.requests.CreateRecivedOrderRequest;
import com.erp.main.app.controller.recivedorder.requests.GetRecivedOrderRequest;
import com.erp.main.app.controller.recivedorder.response.RecivedOrderResponse;
import com.erp.main.app.controller.sales.requests.GetQuotationRequest;
import com.erp.main.app.controller.sales.response.QuotationResponse;
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
	 * 受注票作成
	 * @param request
	 */
	@PostMapping("/recivedorder/register")
	public void createRecivedOrder(@RequestBody CreateRecivedOrderRequest request) {
		this.recivedOrderService.createRecivedOrder(request.mapTo());
	}
	
	
	/**
	 * 受注票取得処理
	 */
	@PostMapping("/recivedOrder/info")
	public RecivedOrderResponse  getRecivedOrder(@RequestBody GetRecivedOrderRequest request) {
		var vo = this.recivedOrderService.getRecivedOrderVo(request.mapTo());
		var response = new RecivedOrderResponse();
//		response.setMaxpage(vo.getMaxpage());
		// TODO: 詳細についてはよく考えて取得結果を返却するようにする
		return response;


}
