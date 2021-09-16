package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.GetWarehouseConditionsVo;

import lombok.Data;

/**
 * 倉庫取得リクエスト
 * @author 高山
 *
 */

@Data
public class GetWarehouseRequest {
	// ページNo
	private Integer pageNo;
    // 倉庫SEQ
    private Long warehouseSeq;
    // 倉庫名
    private String warehouseName;
    
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetWarehouseConditionsVo mapTo() {
        var vo = new GetWarehouseConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 倉庫SEQ
        vo.setWarehouseSeq(this.getWarehouseSeq());
        // 倉庫名
        vo.setWarehouseName(this.getWarehouseName());
        
        return vo;
    }
}
