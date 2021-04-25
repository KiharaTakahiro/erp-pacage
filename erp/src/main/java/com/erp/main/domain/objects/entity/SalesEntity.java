package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SalesEntityFileds;


/**
 * 売上
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Sales")
public class SalesEntity extends SalesEntityFileds {

	
}
