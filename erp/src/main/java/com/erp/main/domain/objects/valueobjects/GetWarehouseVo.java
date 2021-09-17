package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.WarehouseEntity;
import com.erp.main.domain.objects.model.WarehouseModel;

import lombok.Data;

/*
 * 倉庫取得用のVO
 * @author 高山
 */
@Data
public class GetWarehouseVo {
	
	// 倉庫
	private WarehouseModel warehouse;
	
	/**
	 * エンティティからVoへのマッピング
	 * @param entity
	 * @return
	 */
	public static GetWarehouseVo mapTo(WarehouseEntity entity) {

		var model = new WarehouseModel();
		// 倉庫SEQ
		model.setWarehouseSeq(entity.getWarehouseSeq());
		// 倉庫名
		model.setWarehouseName(entity.getName());

		// Voのセット
		var vo = new GetWarehouseVo();
		vo.setWarehouse(model);
				
		return vo;
		
	}
}
