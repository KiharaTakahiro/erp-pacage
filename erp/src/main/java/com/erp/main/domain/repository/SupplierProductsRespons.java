package com.erp.main.domain.repository;

import java.util.List;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.model.SupplierProductModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入商品一覧
 * @author 高山T
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SupplierProductsRespons extends BaseResponse{
	// 最大ページ数
	private Integer maxpage;
	// 仕入れ商品リスト
	List<SupplierProductModel> supplierProducts;
}
