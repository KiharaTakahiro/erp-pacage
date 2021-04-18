package com.erp.main.app.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.main.app.controller.sales.requests.CreateQuotationRequest;
import com.erp.main.domain.services.QuotationService;

/**
 * 販売コントローラ
 * @author takah
 *
 */
@RestController
public class SalesController {
	
	@Autowired
	private QuotationService quotationService;
	
	/**
	 * 見積作成
	 * @param request
	 */
	@PostMapping("/quotation/register")
	public void createQuotation(@RequestBody CreateQuotationRequest request) {
		quotationService.createQuotation(request.mapTo());
	}

}
