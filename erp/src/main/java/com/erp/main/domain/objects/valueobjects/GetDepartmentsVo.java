package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.DepartmentModel;

import lombok.Data;

/*
 * 部署の取得VO
 * @author ngt
 */
@Data
public class GetDepartmentsVo {
	// 取引先リスト
	List<DepartmentModel> department;
	// 全体のページ数
	Integer maxpage;

}
