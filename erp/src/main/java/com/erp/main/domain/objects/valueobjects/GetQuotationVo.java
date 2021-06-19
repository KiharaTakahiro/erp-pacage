package com.erp.main.domain.objects.valueobjects;
import java.util.List;

import com.erp.main.domain.objects.model.QuotationModel;

import lombok.Data;

/**
 * 見積用のValueObject
 * @author takah
 *
 */
@Data
public class GetQuotationVo {
	
	// 見積リスト
	List<QuotationModel> quotations;
	
	// 全体のページ数
	Integer maxpage;

	
}
