package com.erp.main.domain.objects.valueObjects;

import lombok.Data;

/**
 * ロット作成のためのValueObject
 * @author 永戸
 *
 */
@Data
public class CreateLotVo {
	// ロットナンバー
	private String lotNo;
	
	// 製造年月日
	private String lotDate;

}
