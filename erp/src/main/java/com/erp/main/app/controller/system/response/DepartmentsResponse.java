package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.DepartmentModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部署一覧レスポンス
 * @author NGT
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class DepartmentsResponse extends BaseResponse {
	//最大ページ数
	private Integer maxpage;
	
	//部署一覧
	List <DepartmentModel> departments;

}
