package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.OrderDetailEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo.CreateOrderDetailVo;

import lombok.Getter;


/**
 * 発注詳細
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="OrderDetail")
public class OrderDetailEntity extends OrderDetailEntityFileds {

	public static OrderDetailEntity create(CreateOrderDetailVo vo) {
		var entity = new OrderDetailEntity();

		// 発注ステータス
		entity.setStatus(vo.getStatus());
		
		// 商品SEQ
		entity.setSupplierProductSeq(vo.getSupplierProductSeq());
		
		// 数量
		entity.setQuantity(vo.getQuantity());
		
		// 納期
		entity.setDeriveryDate(vo.getDeriveryDate());
		return entity;
}

	/**
	 * 発注テーブルの取得
	 */
	@Getter
	@ManyToOne
	@JoinColumn(name = "ORDER_SEQ", insertable = false, updatable = false)
	private OrderEntity order;
	
	/**
	 * 商品テーブルの取得
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="PRODUCT_SEQ", insertable = false, updatable = false)
	private ProductEntity prodcut;
}
	