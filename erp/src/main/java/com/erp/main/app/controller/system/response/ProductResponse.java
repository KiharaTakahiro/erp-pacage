package com.erp.main.app.controller.system.response;

import com.erp.main.domain.objects.valueobjects.GetProductVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品詳細レスポンス
 * @author ngt
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ProductResponse extends BaseResponse {
	// 商品SEQ
    private Long productSeq;
    
    // 商品名
    private String productName;
    
    // 販売単価
    private Long unitPrice;
    
    // 仕入れ単価
    private Long purchaseUnitPrice;
    
    // 税区分
    // HACK: ここをEnumのまま受けたいが一旦はこのマッピングの中でtaxTypeを数値に変換する
    private Integer taxType;
    
    /**
     * Voからレスポンスへのマッピング
     * @param vo
     * @return
     */
    public static ProductResponse mapTo(GetProductVo vo) {
    	var model = vo.getProduct();
    	var response = new ProductResponse();
    	// 商品SEQ
    	response.setProductSeq(model.getProductSeq());
    	// 商品名
    	response.setProductName(model.getProductName());
    	// 販売単価
    	response.setUnitPrice(model.getUnitPrice());
    	// 仕入単価
    	response.setPurchaseUnitPrice(model.getPurchaseUnitPrice());
    	// 税区分
    	response.setTaxType(model.getTaxType().getCode());
		return response;
	}

}
