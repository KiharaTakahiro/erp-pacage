package com.erp.main.domain.objects.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.QuotationEntityFileds;
import com.erp.main.domain.objects.valueobjects.CreateQuotationVo;

import lombok.Getter;
import lombok.Setter;


/**
 * 見積テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="Quotation")
public class QuotationEntity extends QuotationEntityFileds {
	
	/**
	 * 登録用のエンティティ作成処理
	 * @param vo
	 * @return
	 */
	public static QuotationEntity create(CreateQuotationVo vo) {
		
		var entity = new QuotationEntity();
		
		// 取引先SEQ
		entity.setClientsSeq(vo.getClientsSeq());
		// 会社SEQ
		entity.setCompanySeq(vo.getCompanySeq());
		// 部署SEQ
		entity.setDepartmentSeq(vo.getDepartmentSeq());
		// 作成日
		entity.setCreateDate(vo.getCreateDate());
		// 見積番号
		entity.setQuotationNo(vo.getQuotationNo());
		// 件名
		entity.setSubject(vo.getSubject());
		
		return entity;
	}
	
	/**
	 * 見積詳細
	 */
	@Getter
	@Setter
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "QUOTATION_SEQ", insertable = true, updatable = true)
	private  Set<QuotationDetailEntity> quotationDetailEntity;
	
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
	@JoinColumn(name="DEPARTMENT_SEQ", insertable = false, updatable = false, nullable =true)
	private DepartmentEntity departmentEntity;

}
