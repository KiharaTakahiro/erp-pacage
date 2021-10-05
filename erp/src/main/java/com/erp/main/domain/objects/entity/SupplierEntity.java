package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SupplierEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateSupplierVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierVo;


/**
 * 仕入先
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Supplier")
public class SupplierEntity extends SupplierEntityFileds {
	
	/**
	 * 仕入先エンティティ生成
	 * @param vo
	 * @return
	 */
	public static SupplierEntity create(CreateSupplierVo vo) {

		var entity = new SupplierEntity();
		
		// 仕入先名
		entity.setName(vo.getSupplierName());
		
		return entity;
	}
	
	/**
	 * 仕入先エンティティの更新
	 * @param vo
	 * @return
	 */
	public void update(UpdateSupplierVo vo) {
		
		var model = vo.getSupplier();
		
		// 仕入先名
		this.setName(model.getSupplierName());

	}
}
