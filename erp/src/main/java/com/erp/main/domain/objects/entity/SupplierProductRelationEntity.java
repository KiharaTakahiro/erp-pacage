package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.SupplierProductRelationEntityFields;
import com.erp.main.domain.objects.valueobjects.SupplierProductRelationVo;


/**
 * 仕入れ先商品連関テーブルへのロジック
 * 
 * @author 永戸
 *
 */
@Entity
@Table(name="SupplierProductRelation")
public class SupplierProductRelationEntity extends SupplierProductRelationEntityFields {

	public static SupplierProductRelationEntity create(SupplierProductRelationVo vo) {
		var entity = new SupplierProductRelationEntity();
		
		// 仕入商品Seq
		entity.setSupplierSeq(vo.getSupplierSeq());
		// 仕入商品Seq
		entity.setSupplierProductSeq(vo.getSupplierProductSeq());
		
		return entity;
	}

}
