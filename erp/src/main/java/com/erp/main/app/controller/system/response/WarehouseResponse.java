package com.erp.main.app.controller.system.response;

import com.erp.main.domain.objects.valueobjects.GetWarehouseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 倉庫詳細レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class WarehouseResponse extends BaseResponse {
  	// 倉庫SEQ
    private Long warehouseSeq;
    
    // 倉庫名
    private String warehouseName;

    /**
     * Voからレスポンスへのマッピング
     * @param vo
     * @return
     */
    public static WarehouseResponse mapTo(GetWarehouseVo vo) {
    	var model = vo.getWarehouse();
    	var response = new WarehouseResponse();
    	// 倉庫SEQ
    	response.setWarehouseSeq(model.getWarehouseSeq());
    	// 倉庫名
    	response.setWarehouseName(model.getWarehouseName());
		return response;
	}

}