package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入れ商品一覧
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SupplierProductsResponse extends BaseResponse {
	// 最大ページ数
	private Integer maxpage;
	
	// 仕入れ商品リスト
	List<SupplierProductModel> supplierProducts;
}
