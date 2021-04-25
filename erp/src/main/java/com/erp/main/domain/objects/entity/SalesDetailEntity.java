package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SalesDetailEntityFileds;


/**
 * 売上詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="SalesDetail")
public class SalesDetailEntity extends SalesDetailEntityFileds {

	
}
