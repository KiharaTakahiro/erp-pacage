package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.WarehouseEntityFileds;
import com.erp.main.domain.objects.valueObjects.CreateWarehouseVo;


/**
 * 倉庫
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Warehouse")
public class WarehouseEntity extends WarehouseEntityFileds {

	public static WarehouseEntity create(CreateWarehouseVo vo) {
		
		WarehouseEntity entity = new WarehouseEntity();
		
		entity.setName(vo.getWarehouseName());		
		return entity;
	}

	
}
