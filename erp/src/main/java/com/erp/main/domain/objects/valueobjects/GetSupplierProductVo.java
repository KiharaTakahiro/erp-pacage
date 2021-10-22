package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;

/*
 * 仕入商品の取得VO
 * @author ngt
 */
@Data
public class GetSupplierProductVo {
	
	// 仕入商品リスト
	List<SupplierProductModel> SupplierProduct;
	// 全体のページ数
	Long totalItemsNum;
}
