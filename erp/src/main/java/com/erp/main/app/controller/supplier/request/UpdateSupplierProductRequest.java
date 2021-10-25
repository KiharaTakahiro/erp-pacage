package com.erp.main.app.controller.supplier.request;

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
    	
		// Voの生成
		var vo = new UpdateSupplierProductVo();
		vo.setSupplierProduct(model);
		
		return vo;

	}

}
