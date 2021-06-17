package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ProductEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateProductVo;


/**
 * 商品テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Product")
public class ProductEntity extends ProductEntityFileds {
	
	public static ProductEntity create(CreateProductVo vo) {
		ProductEntity entity = new ProductEntity();
		// 商品名
		entity.setName(vo.getProductName());
		// 金額
		entity.setUnitPrice(vo.getUnitPrice());
		// 仕入れ単価
		entity.setPurchaseUnitPrice(vo.getPurchaseUnitPrice());
		// 税区分
		entity.setTaxType(vo.getTaxType());
		return entity;
	}
}
