package com.erp.main.domain.objects.valueObjects;

import lombok.Data;

/**
 * 会社作成のためのValueObject
 * @author 永戸
 *
 */
@Data
public class CreateCompanyVo {

	/**
	 * 会社名
	 */
	private String name;
	
	/**
	 * 会社郵便番号
	 */
	private String zip;
	
	/**
	 * 会社住所
	 */
	private String address;

	/**
	 * 	会社電話番号
	 */
	private String phone;
}
