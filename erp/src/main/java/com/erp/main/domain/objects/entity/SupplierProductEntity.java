package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.valueobjects.CreateSupplierProductVo;
import com.erp.main.domain.objects.valueobjects.UpdateSupplierProductVo;

/**
 * 商品テーブルへのロジック
 * 
 * @author 高山
 *
 */
@Entity
@Table(name="SupplierProduct")
public class SupplierProductEntity extends SupplierProductEntityFileds {
	
	/**
	 * 仕入商品エンティティ生成
	 * @param vo
	 * @return
	 */
	public static SupplierProductEntity create(CreateSupplierProductVo vo) {

		var entity = new SupplierProductEntity();
		
		// 仕入商品Seq
		entity.setSupplierProductSeq(vo.getSupplierSeq());
		// 仕入商品名
		entity.setName(vo.getSupplierProductName());
		// 値段
		entity.setPurchaseUnitPrice(vo.getPurchaseUnitPrice());
		// 消費税
		entity.setTaxType(vo.getTaxType());	
		
		return entity;
	}

	/*
	 * 仕入商品編集処理のEntity
	 * @param vo
	 */
	
	public void update(UpdateSupplierProductVo vo) {
		// 仕入商品名入れ替え
		this.setName(vo.getSupplierProduct().getSupplierProductName());
	}
}
