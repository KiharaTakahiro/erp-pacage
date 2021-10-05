package com.erp.main.app.controller.sales.requests;

import com.erp.main.domain.objects.model.WarehouseModel;
import com.erp.main.domain.objects.valueobjects.UpdateWarehouseVo;

import lombok.Data;

/*
 * 倉庫取得のリクエスト
 * @author 高山
 */
@Data
public class UpdateWarehouseRequest {
	
	// 倉庫Seq
	private Long warehouseSeq;
	
	// 倉庫名
	private String warehouseName;
	
	/**
	 * Voへのマッピング
	 * @return
	 */
	public  UpdateWarehouseVo mapTo() {
		
		var model = new WarehouseModel();
		// 倉庫SEQ
		model.setWarehouseSeq(this.getWarehouseSeq());
		// 倉庫名
		model.setWarehouseName(this.getWarehouseName());

		// Voの生成
		var vo = new UpdateWarehouseVo();
		vo.setWarehouse(model);
		
		return vo;

	}
	
}
