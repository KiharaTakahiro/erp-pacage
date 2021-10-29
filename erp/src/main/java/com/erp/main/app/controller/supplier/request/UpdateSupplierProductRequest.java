package com.erp.main.app.controller.supplier.request;

import com.erp.main.domain.common.enums.BaseEnum;
import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.model.SupplierProductModel;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierProductVo;

import lombok.Data;

/*
 * 仕入商品取得のリクエスト
 * @author 高山
 */
@Data
public class UpdateSupplierProductRequest {

	
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	// 仕入商品名
	private String supplierProductName;
	
    // 仕入れ価格
    private Long purchaseUnitPrice;
    
	// 税区分
	private Integer taxType;
	
	/**
	 * Voへのマッピング
	 * @return
	 */
	public  UpdateSupplierProductVo mapTo() {		
		var model = new SupplierProductModel();
		// 仕入商品SEQ
		model.setSupplierProductSeq(this.getSupplierProductSeq());
		// 仕入商品名
		model.setSupplierProductName(this.getSupplierProductName());
		// 仕入価格
    	model.setPurchaseUnitPrice(model.getPurchaseUnitPrice());
    	// 税区分
    	model.setTaxType(BaseEnum.of(TaxType.class, this.getTaxType()));
    	
		// Voの生成
		var vo = new UpdateSupplierProductVo();
		vo.setSupplierProduct(model);
		
		return vo;

	}

}
