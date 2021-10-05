package com.erp.main.app.controller.recivedorder.requests;

import com.erp.main.domain.objects.valueobjects.GetSupplierConditionsVo;

import lombok.Data;

/**
 * 仕入先取得リクエスト
 * @author 高山
 *
 */

@Data
public class GetSupplierRequest {
	// ページNo
	private Integer pageNo;
    // 仕入先SEQ
    private Long supplierSeq;
    // 仕入先名
    private String supplierName;
    
    /**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
    public GetSupplierConditionsVo mapTo() {
        var vo = new GetSupplierConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 仕入先SEQ
        vo.setSupplierSeq(this.getSupplierSeq());
        // 仕入先名
        vo.setSupplierName(this.getSupplierName());
        
        return vo;
    }
}
