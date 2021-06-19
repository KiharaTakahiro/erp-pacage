package com.erp.main.app.controller.system.request;

import com.erp.main.domain.objects.valueobjects.CreateCompanyVo;

import lombok.Data;

/**
 * 会社作成APIのリクエスト
 * @author 永戸
 *
 */
@Data
public class CreateCompanyRequest {	
	
	// 会社名
	private String name;
	
	// 会社郵便番号
	private String zip;
	
	// 会社住所
	private String address;
	
	// 会社電話番号
	private String phone;
	
	
	/**
	 * リクエストからValueObjectへの変換
	 * @return
	 */
	public CreateCompanyVo mapTo() {
		
		var vo = new CreateCompanyVo();
		
		// 会社名
		vo.setCompanyName(this.getName());
		// 会社郵便番号
		vo.setCompanyZip(this.getZip());
		// 会社住所
		vo.setCompanyAddress(this.getAddress());
		// 会社電話番号
		vo.setCompanyPhone(this.getPhone());
		
		return vo;
	}
	

}
