package com.erp.main.app.controller.recivedorder.requests;

import com.erp.main.domain.objects.valueobjects.GetRecivedOrderConditionsVo;

import lombok.Data;

/*
 * 受注一覧取得用リクエスト
 */
@Data
public class GetRecivedOrderRequest {
	
	//総ページ数
	private Integer totalItmesNum;
	//受注Seq
	private Long recivedOrderSeq;
	//取引先Seq
	private Long clientsSeq;
	//会社Seq
	private Long companySeq;
	//部署Seq
	private Long departmentSeq;
	//日付から
	private String fromDate;
	//日付まで
	private String toDate;
	//税金から
	private Integer fromTax;
	//税金まで
	private Integer toTax;
	//金額から
	private Integer fromTotal;
	//金額まで
	private Integer toTotal;
	
	/*
	 * リクエストをVoにマッピング
	 * @return
	 */
	
	public GetRecivedOrderConditionsVo mapTo() {
		
		var vo = new GetRecivedOrderConditionsVo();
		
		//総ページ数
		vo.setTotalItemsNum(this.getTotalItmesNum());
		//受注Seq
		vo.setRecivedOrderSeq(this.getRecivedOrderSeq());
		//取引先Seq
		vo.setClientsSeq(this.getClientsSeq());
		//会社Seq
		vo.setCompanySeq(this.getCompanySeq());
		//部署Seq
		vo.setDepartmentSeq(this.getDepartmentSeq());
		//日付から
		vo.setFromDate(this.getFromDate());
		//日付まで
		vo.setToDate(this.getToDate());
		//税金から
		vo.setFromTax(this.getFromTax());
		//税金まで
		vo.setToTax(this.getToTax());
		//金額から
		vo.setFromTotal(this.getFromTotal());
		//金額まで
		vo.setToTotal(this.getToTotal());
		
		return vo;
	}
}
