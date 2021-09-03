package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.ProductModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品一覧レスポンス
 * @author NGT
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProductsResponse extends BaseResponse {
	// 全データの総数
	private Long totalItemsNum;
	
	// 取引先リスト
	List<ProductModel> product;

}
