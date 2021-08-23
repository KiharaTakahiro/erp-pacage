package com.erp.main.domain.objects.model;

import lombok.Data;

/**
 * 会社のモデル
 * @author ngt
 *
 */
@Data
public class CompanyModel {
	
	//会社Seq
	private Long companySeq;
	
	//会社名
	private String companyName;	
	
	/**
	 * 会社郵便番号
	 */
	private String companyZip;
	
	/**
	 * 会社住所
	 */
	private String companyAddress;

	/**
	 * 	会社電話番号
	 */
	private String companyPhone;

}
