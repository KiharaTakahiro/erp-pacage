package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SupplierEntityFileds;
import com.erp.main.domain.objects.valueObjects.CreateSupplierVo;


/**
 * 仕入先
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Supplier")
public class SupplierEntity extends SupplierEntityFileds {
	
	public static SupplierEntity create(CreateSupplierVo vo) {

		SupplierEntity entity = new SupplierEntity();
		
		entity.setName(vo.getSupplierName());
		return entity;
	}
}
