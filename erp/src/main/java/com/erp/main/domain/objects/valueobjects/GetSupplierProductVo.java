package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;

@Data
public class GetSupplierProductVo {
	// 仕入先リスト
	List<SupplierProductModel> supplierProduct;
	// 全体のページ数
	Integer maxpage;
}
