package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.OrderEntityFileds;


/**
 * 発注
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Order")
public class OrderEntity extends OrderEntityFileds {

	
}
