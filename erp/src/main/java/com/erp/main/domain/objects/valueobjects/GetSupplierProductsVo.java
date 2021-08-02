package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;

@Data
public class GetSupplierProductsVo {
	// 仕入先リスト
	List<SupplierProductModel> supplierProducts;
	// 全体のページ数
	Integer maxpage;
}
