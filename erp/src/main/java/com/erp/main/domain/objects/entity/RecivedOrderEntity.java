package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderEntityFileds;


/**
 * 受注
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="RecivedOrder")
public class RecivedOrderEntity extends RecivedOrderEntityFileds {

	
}
