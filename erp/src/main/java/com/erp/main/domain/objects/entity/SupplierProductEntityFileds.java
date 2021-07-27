package com.erp.main.domain.objects.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.erp.main.domain.common.enums.TaxType;
import com.erp.main.domain.common.enums.TaxType.TaxTypeConverter;
import com.erp.main.domain.objects.entity.fields.BaseEntityFields;

import lombok.Getter;
import lombok.Setter;

/**
 * 取引先商品を管理する
 * 
 * @author 永戸
 *
 */
@Setter
@Getter
@MappedSuperclass
public class SupplierProductEntityFileds extends BaseEntityFields {
	/**
	 * 商品SEQ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
    @TableGenerator(name = "seqTable", table = "sequence", pkColumnName = "seq_name", pkColumnValue = "suppllier_product_seq", valueColumnName = "seq_value", initialValue = 1, allocationSize = 1)
	@Column(name = "SUPPLIER_PRODUCT_SEQ")
	private Long SupplierProductSeq;

	/**
	 * 商品名
	 */
	@NotNull
	@Column(name = "NAME")
	private String name;

	/**
	 * 仕入単価
	 */
	@NotNull
	@Column(name="PURCHASE_UNIT_PRICE")
	private Long purchaseUnitPrice;
	
	/**
	 * 税区分
	 */
	@NotNull
	@Column(name="TAX_TYPE")
	@Convert(converter = TaxTypeConverter.class)
	@Enumerated(EnumType.ORDINAL)
	private TaxType taxType;

}
