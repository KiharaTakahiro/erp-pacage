package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.ProductModel;

import lombok.Data;

/*
 * 商品の取得VO
 * @author ngt
 */
@Data
public class GetProductsVo {
	// 取引先リスト
	List<ProductModel> product;
	// 全体のページ数
	Long totalItemsNum;

}
