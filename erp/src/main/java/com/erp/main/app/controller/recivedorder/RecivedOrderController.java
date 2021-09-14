package com.erp.main.app.controller.recivedorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.recivedorder.requests.CreateRecivedOrderRequest;
import com.erp.main.app.controller.recivedorder.requests.GetRecivedOrderRequest;
import com.erp.main.app.controller.recivedorder.requests.GetRecvedOrderRequest;
import com.erp.main.app.controller.recivedorder.response.RecivedOrderResponse;
import com.erp.main.app.controller.recivedorder.response.RecivedOrdersResponse;
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
	public RecivedOrdersResponse  getRecivedOrder(@RequestBody GetRecivedOrderRequest request) {
		var vo = this.recivedOrderService.getRecivedOrderVo(request.mapTo());
		var response = new RecivedOrdersResponse();
		response.setTotalItemsNum(vo.getTotalItemsNum());
		response.setRecivedOrder(vo.getRecivedOder());
		return response;
	}
	
	/**
	 * 受注票詳細取得用の処理
	 */
	@PostMapping("/recivedOrder/edit")
	public RecivedOrderResponse getOrder(@RequestBody GetRecvedOrderRequest request) {
		Long id = request.getRecivedOrderSeq(); 
		var vo = this.recivedOrderService.getRecivedOrderVo(id);
		return RecivedOrderResponse.mapTo(vo);
		
	}


}
