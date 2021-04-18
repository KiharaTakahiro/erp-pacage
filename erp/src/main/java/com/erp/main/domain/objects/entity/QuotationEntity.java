package com.erp.main.domain.objects.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.QuotationEntityFileds;

import lombok.Getter;


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
	 * 見積詳細のテーブル取得
	 */
	@Getter
	@OneToMany
	@JoinColumn(name = "QUOTATION_SEQ", insertable = false, updatable = false)
	private  Set<QuotationDetailEntity> quotationDetail;
	
}
