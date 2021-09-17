package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.ProductTableModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品一覧レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProductsTableResponse extends BaseResponse {
	// 全データの総数
	private Long totalItemsNum;
	
	// 取引先リスト
	List<ProductTableModel> product;

}
