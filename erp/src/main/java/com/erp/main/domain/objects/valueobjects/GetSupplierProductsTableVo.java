package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierProductTableModel;

import lombok.Data;

@Data
public class GetSupplierProductsTableVo {
	// 仕入先リスト
	List<SupplierProductTableModel> supplierProduct;
	// 全体のページ数
	Long totalItemsNum;
}
