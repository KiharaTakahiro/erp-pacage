package com.erp.main.domain.objects.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderEntityFileds;

import lombok.Getter;
import lombok.Setter;


/**
 * 受注
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="RecivedOrder")
public class RecivedOrderEntity extends RecivedOrderEntityFileds {

	/**
	 * 受注詳細
	 */
	@Getter
	@Setter
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "RECIVED_ORDER_SEQ", insertable = true, updatable = true)
	private  Set<RecivedOrderDetailEntity> recivedOrderDetailEntity;
	
	/**
	 * 見積
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="QUOTATION_SEQ", insertable = false, updatable = false)
	private QuotationEntity quotationEntity;
}
