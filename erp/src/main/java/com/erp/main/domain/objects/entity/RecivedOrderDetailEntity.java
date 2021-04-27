package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderDetailEntityFileds;


/**
 * 受注詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="RecivedOrderDetail")
public class RecivedOrderDetailEntity extends RecivedOrderDetailEntityFileds {

	
}
