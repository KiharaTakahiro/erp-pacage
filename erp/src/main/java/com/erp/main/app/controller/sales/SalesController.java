package com.erp.main.app.controller.sales;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.sales.requests.CreateQuotationRequest;
import com.erp.main.app.controller.sales.requests.GetQuotationRequest;
import com.erp.main.app.controller.sales.response.QuotationResponse;
import com.erp.main.domain.services.QuotationService;

/**
 * 販売コントローラ
 * @author takah
 *
 */
@RestController
public class SalesController {

	/**
	 * 見積サービス
	 */
	@Autowired
	private QuotationService quotationService;
	
	/**
	 * 見積作成
	 * @param request
	 */
	@PostMapping("/quotation/register")
	public void createQuotation(@Valid @RequestBody CreateQuotationRequest request) {
		this.quotationService.createQuotation(request.mapTo());
	}
	
	/**
	 * 見積取得処理
	 */
	@PostMapping("quotation/info")
	public QuotationResponse  getQuotation(@RequestBody GetQuotationRequest request) {
		var vo = this.quotationService.getQuotationVo(request.mapTo());
		var response = new QuotationResponse();
		response.setMaxpage(vo.getMaxpage());
		// TODO: 詳細についてはよく考えて取得結果を返却するようにする
		return response;
	}
}
