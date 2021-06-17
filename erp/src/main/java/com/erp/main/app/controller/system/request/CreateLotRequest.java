package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateLotVo;

import lombok.Data;

/**
 * Lot作成APIのリクエスト
 * @author 永戸
 *
 */
@Data
public class CreateLotRequest {
	
	// ロットナンバー
	private String lotNo;
	
	// 製造年月日
	private String lotDate;
	
	public CreateLotVo mapTo() {
		
		var vo = new CreateLotVo();
		vo.setLotNo(getLotNo());
		vo.setLotDate(getLotDate());
		return vo;
	}

}
