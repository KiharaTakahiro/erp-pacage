package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductTableModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入商品一覧
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SupplierProductsTableResponse extends BaseResponse {
	// 最大ページ数
	private Long totalItemsNum;
	
	// 仕入れ商品リスト
	List<SupplierProductTableModel> supplierProduct;
}
