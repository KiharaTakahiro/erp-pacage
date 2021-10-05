package com.erp.main.app.controller.recivedorder.requests;

import com.erp.main.domain.objects.model.SupplierModel;
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
	
	/**
	 * Voへのマッピング
	 * @return
	 */
	public  UpdateSupplierVo mapTo() {
		
		var model = new SupplierModel();
		// 仕入先SEQ
		model.setSupplierSeq(this.getSupplierSeq());
		// 仕入先名
		model.setSupplierName(this.getSupplierName());

		// Voの生成
		var vo = new UpdateSupplierVo();
		vo.setSupplier(model);
		
		return vo;

	}
	
}