package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.model.ProductModel;

import lombok.Data;

/*
 * 商品編集処理のVo
 * @author ngt
 */
@Data
public class UpdateProductVo {
	// 商品
	private ProductModel product;

}
