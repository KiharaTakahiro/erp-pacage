package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.CompanyEntityFileds;


/**
 * 会社テーブルへのロジック
 * 
 * @author 永戸
 *
 */
@Entity
@Table(name="Company")
public class CompanyEntity extends CompanyEntityFileds {
	

	/**
	 * ユーザエンティティの作成
	 *
	 * @param name 会社名
	 * @param zip 会社郵便番号
	 * @param address 会社住所
	 * @param phone 会社電話番号
	 * @return
	 */
	
	public static CompanyEntity create(String name, String zip, String address , String phone) {
		CompanyEntity entity = new CompanyEntity();
		// 会社名
		entity.setName(name);
		// 会社郵便番号
		entity.setZip(zip);
		// 会社住所
		entity.setAddress(address);
		// 会社電話番号
		entity.setPhone(phone);
		return entity;
	}
}
