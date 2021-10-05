package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.SupplierModel;

import lombok.Data;

/*
 * 仕入先の取得VO
 * @author 高山
 */
@Data
public class GetSuppliersVo {
	// 仕入先リスト
	List<SupplierModel> supplier;
	// 全体のページ数
	Long totalItemsNum;

}

