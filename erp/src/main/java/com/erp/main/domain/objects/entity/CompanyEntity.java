package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.CompanyEntityFileds;


/**
 * 会社テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Company")
public class CompanyEntity extends CompanyEntityFileds {

	
}
