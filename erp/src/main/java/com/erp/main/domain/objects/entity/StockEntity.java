package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.StockEntityFileds;


/**
 * 在庫
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Stock")
public class StockEntity extends StockEntityFileds {

	
}
