package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierProductVo;

/**
 * 商品テーブルへのロジック
 * 
 * @author 永戸
 *
 */
@Entity
@Table(name="SupplierProduct")
public class SupplierProductEntity extends SupplierProductEntityFileds {
	
	/**
	 * 仕入先エンティティ生成
	 * @param vo
	 * @return
	 */
	public static SupplierProductEntity create(CreateSupplierProductVo vo) {

		var entity = new SupplierProductEntity();
		
		// 仕入先名
		entity.setName(vo.getSupplierProductName());
		
		return entity;
	}

	/*
	 * 取引先編集処理のEntity
	 * @param vo
	 */
	
	public void update(UpdateSupplierProductVo vo) {
		// 取引先名入れ替え
		this.setName(vo.getSupplierProduct().getSupplierProductName());
	}
}
