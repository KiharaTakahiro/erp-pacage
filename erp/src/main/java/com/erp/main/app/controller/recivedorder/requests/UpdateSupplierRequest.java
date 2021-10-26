package com.erp.main.app.controller.recivedorder.requests;

import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.objects.model.SupplierModel;
import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierVo;

import lombok.Data;

/*
 * 仕入先取得のリクエスト
 * @author 高山
 */
@Data
public class UpdateSupplierRequest {
	
	// 仕入先Seq
	private Long supplierSeq;
	
	// 仕入先名
	private String supplierName;
	
	// 仕入商品Seq
	private Long supplierProductSeq;
	
	// 仕入商品名
	private String supplierProductName;
	
    // 仕入れ価格
    private Long purchaseUnitPrice;
    
	// 消費税
	private TaxType taxType;
	
	/**
	 * Voへのマッピング
	 * @return
	 */
	public  UpdateSupplierVo mapTo() {
		
		var supplierModel = new SupplierModel();
		// 仕入先SEQ
		supplierModel.setSupplierSeq(this.getSupplierSeq());
		// 仕入先名
		supplierModel.setSupplierName(this.getSupplierName());
		
		var SupplierProductVo = new CreateSupplierProductVo();
		// 仕入先Seq
		SupplierProductVo.setSupplierSeq(this.getSupplierSeq());
		// 仕入商品名
		SupplierProductVo.setSupplierProductName(this.getSupplierProductName());
		// 値段
		SupplierProductVo.setPurchaseUnitPrice(this.getPurchaseUnitPrice());
		// 消費税
		SupplierProductVo.setTaxType(this.getTaxType());	
		
		// Voの生成
		var vo = new UpdateSupplierVo();
		vo.setSupplier(supplierModel);
		vo.setSupplierProduct(SupplierProductVo);
		return vo;

	}
	
}