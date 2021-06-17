package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.CompanyEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateCompanyVo;


/**
 * 会社テーブルへのロジック
 * 
 * @author 永戸
 *
 */
@Entity
@Table(name="Company")
public class CompanyEntity extends CompanyEntityFileds {
	
	public static CompanyEntity create(CreateCompanyVo vo) {
		
		CompanyEntity entity = new CompanyEntity();
		
		// 会社名
		entity.setName(vo.getCompanyName());
		// 会社郵便番号
		entity.setZip(vo.getCompanyZip());
		// 会社住所
		entity.setAddress(vo.getCompanyAddress());
		// 会社電話番号
		entity.setPhone(vo.getCompanyPhone());
		return entity;
	}
}
