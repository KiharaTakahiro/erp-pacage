package com.erp.main.app.controller.supplier.request;

import com.erp.main.app.controller.system.response.BaseResponse;
import com.erp.main.domain.objects.valueobjects.GetSupplierProductVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仕入商品詳細レスポンス
 * @author 高山
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SupplierProductResponse extends BaseResponse {
  	// 仕入商品SEQ
    private Long supplierProductSeq;
    
    // 仕入商品名
    private String supplierProductName;
    
    // 
    private Long purchaseUnitPrice;
    
    // 税区分
    private Integer taxType;
    

    /**
     * Voからレスポンスへのマッピング
     * @param vo
     * @return
     */
    public static SupplierProductResponse mapTo(GetSupplierProductVo vo) {
    	var model = vo.getSupplierProduct();
    	var response = new SupplierProductResponse();
    	// 仕入商品SEQ
    	response.setSupplierProductSeq(model.getSupplierProductSeq());
    	// 仕入商品名
    	response.setSupplierProductName(model.getSupplierProductName());
    	// 仕入価格
    	response.setPurchaseUnitPrice(model.getPurchaseUnitPrice());
    	// 税区分
    	response.setTaxType(model.getTaxType().getCode());

    	return response;
	}


}
