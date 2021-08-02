package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;

/*
 * 取引先編集処理のVo
 * @author ngt
 */
@Data
public class UpdateSupplierProductVo {
	// 取引先
	private SupplierProductModel supplierProduct;
}
