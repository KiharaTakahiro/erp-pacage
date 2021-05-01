package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderDetailEntityFileds;

import lombok.Getter;


/**
 * 受注詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="RecivedOrderDetail")
public class RecivedOrderDetailEntity extends RecivedOrderDetailEntityFileds {

	/**
	 * 受注テーブルの取得
	 */
	@Getter
	@ManyToOne
	@JoinColumn(name = "RECIVED_ORDER_SEQ", insertable = false, updatable = false)
	private RecivedOrderEntity recivedOrder;	
}
