package com.erp.main.domain.objects.valueObjects;
import java.util.List;

import lombok.Data;

/**
 * 受注作成用のVo
 * @author 永戸
 *
 */

@Data
public class CreateRecivedOrderVo {
	// 取引先SEQ
	private Long clientsSeq;
	
	// 会社SEQ
	private Long companySeq;
	
	// 部署SEQ
	private Long departmentSeq;
	
	// 見積SEQ
	private Long quotationSeq;
	
	// 受注日
	private String recivedOrderDate;
	
	// 消費税
	private Long tax;
	
	// 合計金額
	private Long total;
	
	private List<CreateRecivedOrderDetailVo> details;
	
	/**
	 * 受注詳細作成用のVo
	 * @author 永戸
	 *
	 */
	@Data
	public static class CreateRecivedOrderDetailVo {

		// 配送日
		private String deriveryDate;
		
		// 値引
		private Long discount;
		
		// ロットSEQ
		private Long lotSeq;
		
		// 商品SEQ
		private Long productSeq;
		
		// 数量
		private Integer quantity;
		
		// 受注SEQ
		private String recivedOrderSeq;
		
	}
		

}
