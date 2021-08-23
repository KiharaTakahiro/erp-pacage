package com.erp.main.domain.objects.valueobjects;

import lombok.Data;

/**
 * 部署作成のためのValueObject
 * @author 永戸
 *
 */
@Data
public class CreateDepartmentVo {
	/**
	 * 会社Seq
	 */
	private Long companySeq;
	
	/**
	 * 部署名
	 */
	private String departmentName;
	
	
}
