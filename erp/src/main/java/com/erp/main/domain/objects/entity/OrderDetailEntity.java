package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.OrderDetailEntityFileds;


/**
 * 発注詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="OrderDetail")
public class OrderDetailEntity extends OrderDetailEntityFileds {

	
}
