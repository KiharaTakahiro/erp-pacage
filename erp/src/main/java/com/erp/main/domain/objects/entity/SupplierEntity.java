package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SupplierEntityFileds;


/**
 * 仕入先
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Supplier")
public class SupplierEntity extends SupplierEntityFileds {

	
}
