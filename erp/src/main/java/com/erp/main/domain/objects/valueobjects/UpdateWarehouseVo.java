package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.model.WarehouseModel;

import lombok.Data;

/*
 * 倉庫編集処理のVo
 * @author 高山
 */
@Data
public class UpdateWarehouseVo {
	// 倉庫
	private WarehouseModel warehouse;

}

