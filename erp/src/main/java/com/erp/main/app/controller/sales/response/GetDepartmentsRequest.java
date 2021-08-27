package com.erp.main.app.controller.sales.response;

import com.erp.main.domain.objects.valueobjects.GetDepartmentConditionsVo;

import lombok.Data;

/*
 * 部署取得のリクエスト
 * @author ngt
 */
@Data
public class GetDepartmentsRequest {
	
	// ページNo
	private Integer pageNo;
    // 部署SEQ
    private Long departmentSeq;
	/**
	 * 会社Seq
	 */
	private Long companySeq;
	
	/**
	 * 部署名
	 */
	private String departmentName;
	
	/**
     * リクエスト空ValueObjectへのマッピング
     * @return
     */
	public GetDepartmentConditionsVo mapTo() {
		var vo = new GetDepartmentConditionsVo();
        // ページ番号
        vo.setPageNo(this.getPageNo());
        // 部署Seq
        vo.setDepartmentSeq(this.departmentSeq);
        // 会社Seq
        vo.setCompanySeq(this.companySeq);
        // 部署名
        vo.setDepartmentName(this.departmentName);
        return vo;
	}

}
