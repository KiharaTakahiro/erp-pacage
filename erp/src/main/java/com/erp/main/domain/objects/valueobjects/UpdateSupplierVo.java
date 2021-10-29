package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.model.SupplierModel;

import lombok.Data;

/*
 * 仕入先編集処理のVo
 * @author 高山
 */
@Data
public class UpdateSupplierVo {
	// 仕入先
	private SupplierModel supplier;
	
	// 仕入商品
	private CreateSupplierProductVo supplierProduct;

}
