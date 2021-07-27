package com.erp.main.domain.objects.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.OrderEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateOrderVo;

import lombok.Getter;
import lombok.Setter;


/**
 * 発注
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Orders")
public class OrderEntity extends OrderEntityFileds {

	public static OrderEntity create(CreateOrderVo vo) {
		
		var entity = new OrderEntity();
		
		// 発注SEQ
		entity.setOrderSeq(vo.getOrderSeq());
		
		// 仕入先SEQ
		entity.setSupplierSeq(vo.getSupplierSeq());
		
		// 発注金額
		entity.setTotal(vo.getTotal());
		
		// 消費税
		entity.setTax(vo.getTax());
		return entity;
}

	/**
	 * 受注詳細
	 */	
	@Getter
	@Setter
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "RECIVED_ORDER_SEQ", insertable = true, updatable = true)
	private Set<OrderDetailEntity> OrderDetailEntity;
	
	/**
	 * 取引先
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="CLIENTS_SEQ", insertable = false, updatable = false)
	private SupplierEntity supplierEntity;
	

} 
