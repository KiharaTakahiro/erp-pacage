package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 部署モデル
 * @author NGT
 *
 */
@Data
public class DepartmentModel {
	/*
	 * 部署Seq
	 */
	private Long departmentSeq;
	
	/**
	 * 会社Seq
	 */
	private Long companySeq;
	
	/**
	 * 部署名
	 */
	private String departmentName;
	

}
