package com.erp.main.app.controller.system.response;

import com.erp.main.domain.common.enums.TaxType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品詳細レスポンス
 * @author ngt
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProductResponse extends BaseResponse{
	// 取引先SEQ
    private Long productSeq;
    
    // 取引先名
    private String productName;
    
    // 販売単価
    private Long unitPrice;
    
    // 仕入れ単価
    private Long purchaseUnitPrice;
    
    // 税区分
    private TaxType taxType;

}
