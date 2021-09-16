package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 倉庫のモデル
 * @author 高山
 *
 */
@Data
public class WarehouseModel {
	// 倉庫Seq
	private Long warehouseSeq;
	
	// 倉庫名
	private String warehouseName;

}
