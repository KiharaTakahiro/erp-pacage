package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ProductEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateProductVo;
import com.erp.main.domain.objects.valueobjects.UpdateProductVo;


/**
 * 商品テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Product")
public class ProductEntity extends ProductEntityFileds {
	
	/**
	 * 商品エンティティの生成
	 * @param vo
	 * @return
	 */
	public static ProductEntity create(CreateProductVo vo) {
		
		var entity = new ProductEntity();
		
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
	
	/**
	 * 商品エンティティの更新
	 * @param vo
	 * @return
	 */
	public void update(UpdateProductVo vo) {
		// モデルを取得
		var model = vo.getProduct();
		// 商品名
		this.setName(model.getProductName());
		// 金額
		this.setUnitPrice(model.getUnitPrice());
		// 仕入れ単価
		this.setPurchaseUnitPrice(model.getPurchaseUnitPrice());
		// 税区分
		this.setTaxType(model.getTaxType());
	}
}
