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
public class ProductResponse extends BaseResponse {
	// 最大ページ数
	private Integer maxpage;
	
	// 取引先リスト
	List<ProductModel> product;

}
