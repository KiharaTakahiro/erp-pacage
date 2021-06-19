package com.erp.main.app.controller.sales.response;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.QuotationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積一覧
 * @author takah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class QuotationResponse extends BaseResponse {

	// 最大ページ数
	private Integer maxpage;
	
	// 見積リスト
	List<QuotationModel> quotations;
	
}
