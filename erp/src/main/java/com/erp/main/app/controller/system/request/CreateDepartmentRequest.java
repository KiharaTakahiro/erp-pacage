package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateDepartmentVo;

import lombok.Data;

/**
 * 部署作成APIのリクエスト
 * @author 永戸
 *
 */
@Data
public class CreateDepartmentRequest {
	
	// 会社SEQ
	private Long companySeq;

	// 部署名
	private String name;
	
	/**
	 * リクエストからValueObjectへのマッピング
	 * @return
	 */
	public CreateDepartmentVo mapTo() {
		
		var vo = new CreateDepartmentVo();
		
		// 会社seq
		vo.setCompanySeq(this.getCompanySeq());
		// 部署名
		vo.setDepartmentName(this.getName());
		
		return vo;
	}
	
}
