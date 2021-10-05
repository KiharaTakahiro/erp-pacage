package com.erp.main.app.controller.recivedorder.response;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.valueobjects.GetSupplierVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入先詳細レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SupplierResponse extends BaseResponse {
  	// 仕入先SEQ
    private Long supplierSeq;
    
    // 仕入先名
    private String supplierName;

    /**
     * Voからレスポンスへのマッピング
     * @param vo
     * @return
     */
    public static SupplierResponse mapTo(GetSupplierVo vo) {
    	var model = vo.getSupplier();
    	var response = new SupplierResponse();
    	// 仕入先SEQ
    	response.setSupplierSeq(model.getSupplierSeq());
    	// 仕入先名
    	response.setSupplierName(model.getSupplierName());
		return response;
	}

}