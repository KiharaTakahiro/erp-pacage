package com.erp.main.domain.objects.valueobjects;

import com.erp.main.domain.objects.entity.SupplierEntity;
import com.erp.main.domain.objects.model.SupplierModel;

import lombok.Data;

/*
 * 仕入先取得用のVO
 * @author 高山
 */
@Data
public class GetSupplierVo {
	
	// 仕入先
	private SupplierModel supplier;
	
	/**
	 * エンティティからVoへのマッピング
	 * @param entity
	 * @return
	 */
	public static GetSupplierVo mapTo(SupplierEntity entity) {

		var model = new SupplierModel();
		// 仕入先SEQ
		model.setSupplierSeq(entity.getSupplierSeq());
		// 仕入先名
		model.setSupplierName(entity.getName());

		// Voのセット
		var vo = new GetSupplierVo();
		vo.setSupplier(model);
				
		return vo;
		
	}
}