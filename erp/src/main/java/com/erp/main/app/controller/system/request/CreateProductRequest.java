package com.erp.main.app.controller.system.request;

import com.erp.main.domain.common.enums.BaseEnum;
import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.valueobjects.CreateProductVo;
import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 商品マスタ作成APIのリクエスト
 * @author 木原
 *
 */
@Data
public class CreateProductRequest {
	
	// 商品名
	@NotNull
	private String productName;
	
	// 単価
	@NotNull
	private Long unitPrice;
	
	// 仕入れ単価
	private Long purchaseUnitPrice;
	
	// 税区分
	// HACK: ここでEnumで受けることもできる気がしますが、一旦これで進める
	private Integer taxType;
	
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateProductVo mapTo() {
		
		var vo = new CreateProductVo();
		
		// 商品名
		vo.setProductName(this.getProductName());
		// 金額
		vo.setUnitPrice(this.getUnitPrice());
		// 仕入単価
		vo.setPurchaseUnitPrice(this.getPurchaseUnitPrice());
		// 税区分
		vo.setTaxType(BaseEnum.of(TaxType.class, this.getTaxType()));
		
		return vo;
	}
	
}
