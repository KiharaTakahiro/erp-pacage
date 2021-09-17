package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.WarehouseModel;

import lombok.Data;

/*
 * 商品の取得VO
 * @author 高山
 */
@Data
public class GetWarehousesVo {
	// 取引先リスト
	List<WarehouseModel> warehouse;
	// 全体のページ数
	Long totalItemsNum;

}
