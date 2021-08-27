package com.erp.main.app.controller.system.response;

import java.util.List;

import com.erp.main.domain.objects.model.CompanyModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会社一覧レスポンス
 * @author NGT
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class CompanysResponse extends BaseResponse {
	//最大ページ数
	private Integer maxpage;
	
	//会社一覧
	List <CompanyModel> companys;

}
