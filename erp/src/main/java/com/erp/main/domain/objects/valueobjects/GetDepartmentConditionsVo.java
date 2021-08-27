package com.erp.main.domain.objects.valueobjects;


import lombok.Data;
/*
 * 部署取得用の検索条件
 */
@Data
public class GetDepartmentConditionsVo {
	// ページ番号
	private Integer pageNo;
	// 部署Seq
	private Long departmentSeq;
	// 会社Seq
	private Long companySeq;
	// 部署名
	private String departmentName;

}
