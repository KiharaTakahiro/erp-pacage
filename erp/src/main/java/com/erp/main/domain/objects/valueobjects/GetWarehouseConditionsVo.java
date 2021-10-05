package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 倉庫取得用の検索条件
 * 
 */

@Data
public class GetWarehouseConditionsVo {
	// ページ番号
	private Integer pageNo;
	// 倉庫Seq
	private Long warehouseSeq;
	// 倉庫名
	private String warehouseName;

}
