package com.erp.main.app.controller.system.request;

import lombok.Data;

/**
 * 会社作成APIのリクエスト
 * @author 永戸
 *
 */
@Data
public class CreateCompanyRequest {
	
	/**
	 * 会社住所
	 */
	private String companyAddress;
	
	/**
	 * 会社名
	 */
	private String companyName;
	
	/**
	 * 	会社電話番号
	 */
	private String companyPhone;
	
	/**
	 * 会社郵便番号
	 */
	private String companyZip;
	

}
