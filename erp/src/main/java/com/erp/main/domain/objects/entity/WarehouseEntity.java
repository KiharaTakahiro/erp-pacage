package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.WarehouseEntityFileds;


/**
 * 倉庫
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Warehouse")
public class WarehouseEntity extends WarehouseEntityFileds {

	
}
