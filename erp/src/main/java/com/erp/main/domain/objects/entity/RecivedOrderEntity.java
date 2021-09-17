package com.erp.main.domain.objects.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.RecivedOrderEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateRecivedOrderVo;
import com.erp.main.domain.objects.valueobjects.UpdateRecivedOrderVo;

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
	 * 登録用のエンティティ作成処理
	 * @param vo
	 * @return
	 */
	
	public static RecivedOrderEntity create(CreateRecivedOrderVo vo ) {
		
		var entity = new RecivedOrderEntity();
		
		// 取引先SEQ
		entity.setClientsSeq(vo.getClientsSeq());
		// 会社SEQ
		entity.setCompanySeq(vo.getCompanySeq());
		// 部署SEQ
		entity.setDepartmentSeq(vo.getDepartmentSeq());
		// 見積SEQ
		entity.setQuotationSeq(vo.getQuotationSeq());
		// 受注日
		entity.setRecivedOrderDate(vo.getRecivedOrderDate());
		// 消費税
		entity.setTax(vo.getTax());
		// 合計金額
		entity.setTotal(vo.getTotal());
		
		return entity;

	}
	
	/*
	 * 更新用のエンティティ処理
	 * @author ngt
	 */
	public void update(UpdateRecivedOrderVo vo) {
		var recivedOrder = vo.getRecivedOrder();
		//
		this.setRecivedOrderSeq(recivedOrder.getRecivedOrderSeq());
		//
		this.setClientsSeq(recivedOrder.getClientsSeq());
		//
		this.setCompanySeq(recivedOrder.getCompanySeq());
		//
		this.setDepartmentSeq(recivedOrder.getDepartmentSeq());
		//
		this.setRecivedOrderDate(recivedOrder.getRecivedOrderDate());
		//
		this.setQuotationSeq(recivedOrder.getQuotationSeq());
		//
		this.setTax(recivedOrder.getTax());
		//
		this.setTotal(recivedOrder.getTotal());
	}

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
//	@Getter
//	@OneToOne
//	@JoinColumn(name="QUOTATION_SEQ", insertable = false, updatable = false)
//	private QuotationEntity quotationEntity;
	
	/**
	 * 取引先
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="CLIENTS_SEQ", insertable = false, updatable = false)
	private ClientsEntity clientsEntity;

	/**
	 * 会社
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="COMPANY_SEQ", insertable = false, updatable = false)
	private CompanyEntity companyEntity;

	/**
	 * 部署
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="DEPARTMENT_SEQ", insertable = false, updatable = false)
	private DepartmentEntity departmentEntity;
	
}
