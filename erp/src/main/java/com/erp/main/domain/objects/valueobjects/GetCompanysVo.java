package com.erp.main.domain.objects.valueobjects;

import java.util.List;

import com.erp.main.domain.objects.model.CompanyModel;

import lombok.Data;

/*
 * 取引先の取得VO
 * @author ngt
 */
@Data
public class GetCompanysVo {

	// 取引先リスト
	List<CompanyModel> company;
	// 全体のページ数
	Integer maxpage;
}
