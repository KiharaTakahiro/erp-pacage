package com.erp.main.domain.objects.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.erp.main.domain.objects.entity.fields.QuotationDetailEntityFileds;

import lombok.Getter;

/**
 * 見積詳細テーブルへのロジック
 * 
 * @author 木原
 *
 */
@Entity
@Table(name="QuotationDetail")
public class QuotationDetailEntity extends QuotationDetailEntityFileds {

	/**
	 * 見積テーブルの取得
	 */
	@Getter
	@ManyToOne
	@JoinColumn(name = "QUOTATION_SEQ", insertable = false, updatable = false)
	private QuotationEntity quotation;
	
	/**
	 * 商品テーブルの取得
	 */
	@Getter
	@OneToOne
	@JoinColumn(name="PRODUCT_SEQ", insertable = false, updatable = false)
	private ProductEntity prodcut;
}
