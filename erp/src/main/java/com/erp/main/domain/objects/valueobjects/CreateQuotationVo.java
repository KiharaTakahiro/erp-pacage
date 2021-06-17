package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import lombok.Data;

/**
 * 見積作成用のVo
 * @author takah
 *
 */
@Data
public class CreateQuotationVo {

	/**
	 * 取引先SEQ
	 */
	private Long clientsSeq;

	/**
	 * 部門SEQ
	 */
	private Long departmentSeq;

	/**
	 * 会社SEQ
	 */
	private Long companySeq;
	
	/**
	 * 見積番号
	 */
	private String quotationNo;
	
	/**
	 * 作成日
	 */
	private String createDate;
		
	/**
	 * 件名
	 */
	private String subject;
	
	/**
	 * 値引合計
	 */
	private Long discountTotal;
	
	/**
	 * 見積詳細のVo
	 */
	private List<CreateQuotationDetailVo> details;
		
	/**
	 * 見積詳細部分のVO
	 * @author takah
	 *
	 */
	@Data
	public static class CreateQuotationDetailVo {
		
		/**
		 * 商品SEQ
		 */
		private Long productSeq;
		
		/**
		 * 数量
		 */
		private Integer quantity;
		
		/**
		 * 値引
		 */
		private Long discount;

	}
}
