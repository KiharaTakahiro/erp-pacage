package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.ProductEntityFileds;


/**
 * 商品テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Product")
public class ProductEntity extends ProductEntityFileds {

	
}
